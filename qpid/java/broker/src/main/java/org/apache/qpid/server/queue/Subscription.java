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
package org.apache.qpid.server.queue;

import org.apache.qpid.AMQException;

import java.util.Queue;

public interface Subscription
{
    void send(AMQMessage msg, AMQQueue queue) throws FailedDequeueException;

    boolean isSuspended();

    void queueDeleted(AMQQueue queue);

    boolean hasFilters();

    boolean hasInterest(AMQMessage msg);

    Queue<AMQMessage> getPreDeliveryQueue();

    void enqueueForPreDelivery(AMQMessage msg);

    boolean isAutoClose();

    void close();

    boolean isBrowser();

    Queue<AMQMessage> getResendQueue();

    Queue<AMQMessage> getNextQueue(Queue<AMQMessage> messages);

    void addToResendQueue(AMQMessage msg);

    Object sendlock();
}
