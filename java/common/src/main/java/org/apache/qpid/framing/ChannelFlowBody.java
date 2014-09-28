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

/*
 * This file is auto-generated by Qpid Gentools v.0.1 - do not modify.
 * Supported AMQP version:
 *   8-0
 */

package org.apache.qpid.framing;

import java.io.DataOutput;
import java.io.IOException;

import org.apache.qpid.AMQException;
import org.apache.qpid.codec.MarkableDataInput;

public class ChannelFlowBody extends AMQMethodBodyImpl implements EncodableAMQDataBlock, AMQMethodBody
{
    private static final AMQMethodBodyInstanceFactory FACTORY_INSTANCE = new AMQMethodBodyInstanceFactory()
    {
        public AMQMethodBody newInstance(MarkableDataInput in, long size) throws AMQFrameDecodingException, IOException
        {
            return new ChannelFlowBody(in);
        }
    };

    public static AMQMethodBodyInstanceFactory getFactory()
    {
        return FACTORY_INSTANCE;
    }

    public static final int CLASS_ID =  20;
    public static final int METHOD_ID = 20;

    // Fields declared in specification
    private final byte _bitfield0; // [active]

    // Constructor
    public ChannelFlowBody(MarkableDataInput buffer) throws AMQFrameDecodingException, IOException
    {
        _bitfield0 = readBitfield( buffer );
    }

    public ChannelFlowBody(
            boolean active
                          )
    {
        byte bitfield0 = (byte)0;
        if( active )
        {
            bitfield0 = (byte) (((int) bitfield0) | (1 << 0));
        }
        _bitfield0 = bitfield0;
    }

    public int getClazz()
    {
        return CLASS_ID;
    }

    public int getMethod()
    {
        return METHOD_ID;
    }

    public final boolean getActive()
    {
        return (((int)(_bitfield0)) & ( 1 << 0)) != 0;
    }

    protected int getBodySize()
    {
        int size = 1;
        return size;
    }

    public void writeMethodPayload(DataOutput buffer) throws IOException
    {
        writeBitfield( buffer, _bitfield0 );
    }

    public boolean execute(MethodDispatcher dispatcher, int channelId) throws AMQException
	{
        return dispatcher.dispatchChannelFlow(this, channelId);
	}

    public String toString()
    {
        StringBuilder buf = new StringBuilder("[ChannelFlowBodyImpl: ");
        buf.append( "active=" );
        buf.append(  getActive() );
        buf.append("]");
        return buf.toString();
    }

}
