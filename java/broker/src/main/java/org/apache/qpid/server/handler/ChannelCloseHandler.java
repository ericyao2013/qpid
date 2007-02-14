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
package org.apache.qpid.server.handler;

import org.apache.qpid.AMQException;
import org.apache.qpid.framing.ChannelCloseBody;
import org.apache.qpid.protocol.AMQMethodEvent;
import org.apache.qpid.server.protocol.AMQProtocolSession;
import org.apache.qpid.server.state.AMQStateManager;
import org.apache.qpid.server.state.StateAwareMethodListener;

import org.apache.log4j.Logger;

public class ChannelCloseHandler implements StateAwareMethodListener<ChannelCloseBody>
{
    private static final Logger _logger = Logger.getLogger(ChannelCloseHandler.class);

    private static ChannelCloseHandler _instance = new ChannelCloseHandler();

    public static ChannelCloseHandler getInstance()
    {
        return _instance;
    }

    private ChannelCloseHandler() {}

    public void methodReceived(AMQStateManager stateManager, AMQMethodEvent<ChannelCloseBody> evt) throws AMQException
    {
        AMQProtocolSession session = stateManager.getProtocolSession();
        final ChannelCloseBody body = evt.getMethod();
        _logger.info("Received channel close for id " + evt.getChannelId() + " citing class " +
            body.classId + " and method " + body.methodId);
        session.closeChannelResponse(evt.getChannelId(), evt.getRequestId());
    }
}
