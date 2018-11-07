package com.tao.test;  
  
import java.awt.FlowLayout;  
import java.awt.Window;  
import java.awt.event.ActionEvent;  
import java.awt.event.ActionListener;  
  
import javax.swing.JButton;
import javax.swing.JFrame;  
import javax.swing.JTextField;  
import javax.swing.UIManager;  
import javax.swing.UnsupportedLookAndFeelException;  
  
  
public class MainFrame extends JFrame {  
  
    public MainFrame(){  
        init();  
    }  
    Object[] value;
    Object[] defaultValue;
    private void init() {  
          
        this.setLayout(new FlowLayout());  
         value = new String[]{ "1" , "2" ,"3" ,"4","5" };  
         defaultValue = new String[]{ "1"  ,"4","5" };  
        final MulitCombobox mulit = new MulitCombobox(value, defaultValue);  
        final JTextField text = new JTextField(20);  
        mulit.addActionListener(new ActionListener() {  
              
            @Override  
            public void actionPerformed(ActionEvent e) {  
                Object[] v = mulit.getSelectedValues();  
                StringBuilder builder = new StringBuilder();  
                for(Object dv : v){  
                      
                builder.append(dv);  
                builder.append("--");  
                }  
                text.setText(builder.toString());  
            }  
        });  
        this.add(mulit );  
          
        this.add(text);  
        JButton ok = new JButton("È·¶¨");
        this.add(ok);
        ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				   value = new String[]{ "q" , "w" ,"4e" ,"5","h" };  
			       defaultValue = new String[]{ "q" , "w" ,"4e" ,"5","h" };  
			       mulit.MulitCombobox_all(value,defaultValue);
			}
		});
        JButton ok1 = new JButton("xx");
        this.add(ok1);
        ok1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Object[] v = mulit.getSelectedValues();  
				StringBuilder builder = new StringBuilder(); 
				 for(Object dv : v){  
		                builder.append(dv);  
		                builder.append("--");  
		                } 
				 System.out.println("sssssssss:"+builder);
			}
		});
    }  
    /** 
     * @param args 
     */  
    public static void main(String[] args) {  
       /* try {  
           // UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");  
        } catch (ClassNotFoundException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        } catch (InstantiationException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        } catch (IllegalAccessException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        } catch (UnsupportedLookAndFeelException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  */
          
          
        Window main = new MainFrame();  
        main.setSize(400,400);  
        main.setVisible(true);  
  
    }  
  
}  
