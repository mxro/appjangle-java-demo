/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appjangle.demos.appjanglejavademo;

import io.nextweb.Node;
import io.nextweb.NodeList;
import io.nextweb.Session;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author <a href="http://www.mxro.de">Max Rohde</a>
 */
public class Calculations {
    
    Types t;
    Session session;
    
    public Map<String, String> calculatePostsPerUser(Node posts) {
        Map<String, String> res = new HashMap<String, String>();
        
        NodeList allPosts = posts.selectAll(t.aPost()).get();
        
        for (Node post : allPosts) {
            
        }
        
        return res;
    }

    public Calculations(Session session) {
        this.session = session;
        this.t = new Types(session);
    }
    
    
    
}
