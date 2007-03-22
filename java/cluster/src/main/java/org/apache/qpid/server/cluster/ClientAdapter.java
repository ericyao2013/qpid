/*
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *
 */
package org.apache.qpid.server.cluster;

import org.apache.mina.common.IoSession;
import org.apache.qpid.AMQException;
import org.apache.qpid.client.AMQConnection;
import org.apache.qpid.protocol.AMQMethodEvent;
import org.apache.qpid.client.protocol.AMQProtocolSession;
import org.apache.qpid.client.state.AMQStateManager;
import org.apache.qpid.framing.AMQMethodBodyImpl;

/**
 * Hack to assist with reuse of the client handlers for connection setup in
 * the inter-broker communication within the cluster.
 *
 */
class ClientAdapter implements MethodHandler
{
    private final AMQProtocolSession _session;
    private final AMQStateManager _stateMgr;

    ClientAdapter(IoSession session, AMQStateManager stateMgr)
    {
        this(session, stateMgr, "guest", "guest", session.toString(), "/cluster");
    }

    ClientAdapter(IoSession session, AMQStateManager stateMgr, String user, String password, String name, String path)
    {
        _session = new SessionAdapter(session, new ConnectionAdapter(user, password, name, path));
        _stateMgr = stateMgr;
    }

    public void handle(int channel, AMQMethodBodyImpl method) throws AMQException
    {
        AMQMethodEvent evt = new AMQMethodEvent(channel, method);
        _stateMgr.methodReceived(evt);
    }

    private class SessionAdapter extends AMQProtocolSession
    {
        public SessionAdapter(IoSession session, AMQConnection connection)
        {
            super(null, session, connection);
        }
    }

    private static class ConnectionAdapter extends AMQConnection
    {
        ConnectionAdapter(String username, String password, String clientName, String virtualPath)
        {
            super(username, password, clientName, virtualPath);
        }
    }
}
