/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sschatz.jspdemo.util;

import com.sschatz.jspdemo.commons.Constants;
import com.sschatz.jspdemo.web.ProcedimentoPacienteServletForm;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author sschatz
 */
public class ServletTools {
    
    public static boolean checkId(HttpServletRequest request)
    {
       System.out.println("ID  = " + request.getParameter(Constants.ID));
       return request.getParameterMap().containsKey(Constants.ID) ? 
               Long.parseLong(request.getParameter(Constants.ID)) > 0 :
               false;
    }
    
    public static String getAction(HttpServletRequest request)
    {
       if ( request.getParameterMap().containsKey(Constants.NEW) )
       {
           return Constants.NEW;
       }
       else if ( request.getParameterMap().containsKey(Constants.DELETE) )
       {
           return Constants.DELETE;
       }
       else if ( request.getParameterMap().containsKey(Constants.EDIT) )
       {
           return Constants.EDIT;
       }
       else {
           return Constants.DEFAULT;
       }
               
    }
    
     public static void displayMessage(HttpServletResponse response, String message) {
        PrintWriter out; 
        try {
            out = response.getWriter();
            out.println("<script type='text/javascript'>");
            out.println("alert(" + "'" + message + "'" + ");</script>");
            out.println("</head><body></body></html>");
        } catch (IOException ex) {
            Logger.getLogger(ProcedimentoPacienteServletForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
     
     public static void printParameters(HttpServletRequest request) {
        Iterator it = request.getParameterMap().entrySet().iterator();
        while (it.hasNext()) {
            System.out.println(it.next().toString());
        }
     }
}
