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
 *   0-9
 */

package org.apache.qpid.framing.amqp_0_9;

import org.apache.qpid.codec.MarkableDataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.qpid.framing.*;
import org.apache.qpid.AMQException;

public class StreamQosBodyImpl extends AMQMethodBody_0_9 implements StreamQosBody
{
    private static final AMQMethodBodyInstanceFactory FACTORY_INSTANCE = new AMQMethodBodyInstanceFactory()
    {
        public AMQMethodBody newInstance(MarkableDataInput in, long size) throws AMQFrameDecodingException, IOException
        {
            return new StreamQosBodyImpl(in);
        }
    };

    public static AMQMethodBodyInstanceFactory getFactory()
    {
        return FACTORY_INSTANCE;
    }

    public static final int CLASS_ID =  80;
    public static final int METHOD_ID = 10;

    // Fields declared in specification
    private final long _prefetchSize; // [prefetchSize]
    private final int _prefetchCount; // [prefetchCount]
    private final long _consumeRate; // [consumeRate]
    private final byte _bitfield0; // [global]

    // Constructor
    public StreamQosBodyImpl(MarkableDataInput buffer) throws AMQFrameDecodingException, IOException
    {
        _prefetchSize = readUnsignedInteger( buffer );
        _prefetchCount = readUnsignedShort( buffer );
        _consumeRate = readUnsignedInteger( buffer );
        _bitfield0 = readBitfield( buffer );
    }

    public StreamQosBodyImpl(
                                long prefetchSize,
                                int prefetchCount,
                                long consumeRate,
                                boolean global
                            )
    {
        _prefetchSize = prefetchSize;
        _prefetchCount = prefetchCount;
        _consumeRate = consumeRate;
        byte bitfield0 = (byte)0;
        if( global )
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

    public final long getPrefetchSize()
    {
        return _prefetchSize;
    }
    public final int getPrefetchCount()
    {
        return _prefetchCount;
    }
    public final long getConsumeRate()
    {
        return _consumeRate;
    }
    public final boolean getGlobal()
    {
        return (((int)(_bitfield0)) & ( 1 << 0)) != 0;
    }

    protected int getBodySize()
    {
        int size = 11;
        return size;
    }

    public void writeMethodPayload(DataOutput buffer) throws IOException
    {
        writeUnsignedInteger( buffer, _prefetchSize );
        writeUnsignedShort( buffer, _prefetchCount );
        writeUnsignedInteger( buffer, _consumeRate );
        writeBitfield( buffer, _bitfield0 );
    }

    public boolean execute(MethodDispatcher dispatcher, int channelId) throws AMQException
	{
    return ((MethodDispatcher_0_9)dispatcher).dispatchStreamQos(this, channelId);
	}

    public String toString()
    {
        StringBuilder buf = new StringBuilder("[StreamQosBodyImpl: ");
        buf.append( "prefetchSize=" );
        buf.append(  getPrefetchSize() );
        buf.append( ", " );
        buf.append( "prefetchCount=" );
        buf.append(  getPrefetchCount() );
        buf.append( ", " );
        buf.append( "consumeRate=" );
        buf.append(  getConsumeRate() );
        buf.append( ", " );
        buf.append( "global=" );
        buf.append(  getGlobal() );
        buf.append("]");
        return buf.toString();
    }

}