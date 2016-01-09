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

import org.sonatype.gossip.Event;
import org.sonatype.gossip.Level;
import org.sonatype.gossip.render.PatternRenderer;

import static org.fusesource.jansi.Ansi.Attribute.INTENSITY_BOLD;
import static org.fusesource.jansi.Ansi.Color.DEFAULT;
import static org.fusesource.jansi.Ansi.Color.RED;
import static org.fusesource.jansi.Ansi.Color.YELLOW;
import static org.fusesource.jansi.Ansi.ansi;

/**
 * Maven specific renderer.
 *
 * @author Tamas Cservenak
 * @since 3.3.9
 */
public class MavenRenderer
    extends PatternRenderer
{
    private boolean colorize = false; // be on safe side

    public boolean isColorize()
    {
        return colorize;
    }

    public void setColorize( final boolean colorize )
    {
        this.colorize = colorize;
    }

    @Override
    public String render( final Event event )
    {
        String buff = super.render( event );
        if ( colorize )
        {
            return colorize( event.getLevel(), buff );
        }
        else
        {
            return buff;
        }
    }

    private String colorize( final Level level, final String buff )
    {
        assert level != null;
        assert buff != null;

        switch ( level )
        {
            case TRACE:
            case DEBUG:
                return ansi().a( INTENSITY_BOLD ).fg( YELLOW ).a( buff ).reset().toString();

            case INFO:
                return ansi().a( INTENSITY_BOLD ).fg( DEFAULT ).a( buff ).reset().toString();

            case WARN:
            case ERROR:
                return ansi().a( INTENSITY_BOLD ).fg( RED ).a( buff ).reset().toString();

            default:
                throw new IllegalArgumentException( "Unknown level: " + level );
        }
    }
}
