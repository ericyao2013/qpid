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
package org.apache.qpid.client.message;

import java.util.LinkedList;
import java.util.List;

/**
 * This class contains everything needed to process a JMS message.
 *
 * Note that the actual work of creating a JMS message for the client code's use is done
 * outside of the MINA dispatcher thread in order to minimise the amount of work done in
 * the MINA dispatcher thread.
 *
 */
public class UnprocessedMessage
{
	private int bytesReceived = 0;
	private int channelId;
	private List<byte[]> contents = new LinkedList();
	private long deliveryTag;
	private MessageHeaders messageHeaders;
    
    public UnprocessedMessage(int channelId, long deliveryTag, MessageHeaders messageHeaders)
    {
        this.channelId = channelId;
        this.deliveryTag = deliveryTag;
        this.messageHeaders = messageHeaders;
    }
    
    public UnprocessedMessage(int channelId, long deliveryTag, MessageHeaders messageHeaders, byte[] content)
    {
        this.channelId = channelId;
        this.deliveryTag = deliveryTag;
        this.messageHeaders = messageHeaders;
        addContent(content);
    }

	public void addContent(byte[] content)
    {
		contents.add(content);
		bytesReceived += content.length;
	}

    public int getBytesReceived()
    {
        return bytesReceived;
    }

    public int getChannelId()
    {
        return channelId;
    }
    
    public List<byte[]> getContents()
    {
        return contents;
    }

    public long getDeliveryTag()
    {
        return deliveryTag;
    }
    
    public MessageHeaders getMessageHeaders()
    {
        return messageHeaders;
    }
    
    public String toString()
    {
        return "UnprocessedMessage: ch=" + channelId + "; bytesReceived=" + bytesReceived + "; deliveryTag=" + deliveryTag + "; MsgHdrs=" + messageHeaders + "Num contents=" + contents.size() + "; First content=" + new String(contents.get(0));
    }
}
