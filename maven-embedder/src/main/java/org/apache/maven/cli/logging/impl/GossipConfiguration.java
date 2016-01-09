package org.apache.maven.cli.logging.impl;

/*
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
 */

import org.sonatype.gossip.Gossip;
import org.sonatype.gossip.Gossip.LoggerImpl;

import org.apache.maven.cli.logging.BaseSlf4jConfiguration;

/**
 * Configuration for gossip-slf4j.
 *
 * @author Tamas Cservenak
 * @since 3.3.9
 */
public class GossipConfiguration
    extends BaseSlf4jConfiguration
{
    org.sonatype.gossip.Level value = org.sonatype.gossip.Level.INFO;

    @Override
    public void setRootLoggerLevel( Level level )
    {
        switch ( level )
        {
            case DEBUG:
                value = org.sonatype.gossip.Level.DEBUG;
                break;

            case ERROR:
                value = org.sonatype.gossip.Level.ERROR;
                break;

            default:
                throw new IllegalArgumentException( "Unknown level:" + level );
        }
    }

    @Override
    public void activate()
    {
        LoggerImpl root = Gossip.getInstance().getRoot();
        root.setLevel( value );
    }
}
