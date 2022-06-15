package com.aristo.action;

import java.awt.Color;
import java.io.OutputStream;
import java.sql.Connection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.category.DefaultCategoryDataset;

import com.aristo.dao.SQLGraphGenDAO;
import com.aristo.valueobject.LoginFormBean;
import com.lowagie.text.Font;



public class GraphGenAction extends DispatchAction {

	
	public ActionForward GraphGen(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception 
	{
		
      try
        {
    	  
          System.out.println("Calleed Graph Gen Action class");       
          
          DataSource datasource = this.getDataSource(req,"userDB"); 
    	  Connection con=datasource.getConnection();

          HttpSession session=req.getSession();
          LoginFormBean lfb=(LoginFormBean)session.getAttribute("Login");

          if (lfb==null)

          {
          	return mapping.findForward("sfail");
          }
          
          int depo_code=lfb.getCode(); 
          int div_code=lfb.getDiv_code();
          String tp = lfb.getD_type();
          String Head = "Target Vs Sales ("+lfb.getBranch_name()+")";

          int eyear=0;
          
          Iterator itt = lfb.getYlist().iterator();
	  		if (itt.hasNext())
	  		{
	  			lfb=(LoginFormBean)(itt.next());
	  			eyear=lfb.getYval();
	  		}                

            OutputStream out = res.getOutputStream();
            
            SQLGraphGenDAO sg = new SQLGraphGenDAO();
            //DefaultCategoryDataset dataset=sg.getGraphGen(con, depo_code,div_code, eyear);
            DefaultCategoryDataset dataset=sg.getGraphGenOld(con, depo_code,tp, eyear);
            
            //JFreeChart chart =ChartFactory.createBarChart3D(Head,"Months","Value Wise", dataset,PlotOrientation.VERTICAL,true,true,true);
            JFreeChart chart =ChartFactory.createBarChart(Head,"Months","Value Wise", dataset,PlotOrientation.VERTICAL,true,true,true);
            chart.setBackgroundPaint(Color.white);
             
             
            
            // Set the background colour of the chart
            chart.getTitle().setPaint(Color.GRAY);
            chart.getTitle().setFont(new java.awt.Font("SansSerif", 1, 14));
            
            
            // Adjust the colour of the title
            CategoryPlot plot = chart.getCategoryPlot();
            BarRenderer br = (BarRenderer) plot.getRenderer();
           // br.setMaximumBarWidth(.35); // set maximum width to 35% of chart
            
            // Get the Plot object for a bar graph
            plot.setBackgroundPaint(Color.white);      
            plot.setRangeGridlinePaint(Color.GRAY);
           
            plot.getDomainAxis().setTickLabelFont(new java.awt.Font("SansSerif", 1, 12));
            plot.getRangeAxis().setTickLabelFont(new java.awt.Font("SansSerif", 1, 12));
            plot.getDomainAxis().setLabelFont(new java.awt.Font("SansSerif", 1, 12));
            plot.getRangeAxis().setLabelFont(new java.awt.Font("SansSerif", 1, 12));
            
            CategoryItemRenderer renderer = plot.getRenderer();

            renderer.setSeriesPaint(0, new Color(165,42,42));
            renderer.setSeriesPaint(1, new Color(140,179,105));
            renderer.setSeriesPaint(2, new Color(247,181,56));
            br.setItemMargin(0);
            br.setBarPainter(new StandardBarPainter());
            br.setLegendTextFont(0, new java.awt.Font("SansSerif", 1, 12));
            br.setLegendTextFont(1, new java.awt.Font("SansSerif", 1, 12));
            br.setLegendTextFont(2, new java.awt.Font("SansSerif", 1, 12));
            
            
	        res.setContentType("image/png");
	        
	        ChartUtilities.writeChartAsPNG(out, chart, 850, 400);
//	        ChartUtilities.writeChartAsPNG(out, null, 850, 400);

           } 
           catch (Exception e) 
           {  
        	   System.err.println("Problem occurred creating chart." + e.getMessage());
           }
         	return null;

      }

}