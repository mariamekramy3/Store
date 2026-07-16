import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import java.util.*;
public class StoreGUI {
	int height = 600;
	int width = 600;
	
	ProductServices service = new ProductServices();
	JButton add = new JButton ("Add Product");
	JButton editordelete = new JButton ("Edit or Delete");
	JButton show = new JButton ("Show products");
	JLabel nameQ = new JLabel ("Enter the name of the Product");
	JLabel priceQ = new JLabel ("Enter the price of the Product");
	JLabel idQ = new JLabel ("Enter the ID of the product");
	JTextField ProductName = new JTextField(8);
	JTextField ProductPrice = new JTextField(8);
	JTextField ProductID = new JTextField(8);
	JButton adder = new JButton ("Add");
	JButton edit = new JButton("Edit");
	JButton deleteProd = new JButton("Delete");
	JButton filterName = new JButton ("Filter Name");
	JButton filterPrice  = new JButton ("Filter Price");
	JFrame frame = new JFrame("Store");
	
	
	
	StoreGUI() {
		frame.setSize(height, width);
		frame.setBackground(Color.WHITE);
		frame.setLayout(new BorderLayout());
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		JLabel label = new JLabel();
		label.setFont(new Font("Times New Roman", Font.BOLD, 80));
		label.setText("Welcome to Store");
		label.setBackground(Color.WHITE);
		
		
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setBackground(Color.WHITE);
		panel.add(label);
		frame.add(panel);
		
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
		JButton[] buttons = {add, editordelete, show};
		for (int i = 0; i <buttons.length; i++){
			buttons[i].setFont(new Font("Times New Roman", Font.BOLD, 20));
			buttons[i].setSize(40, 50);
			buttons[i].setAlignmentX(Component.CENTER_ALIGNMENT);
			buttonPanel.add(buttons[i]);
			buttonPanel.add(Box.createVerticalStrut(10));
		}
		frame.add(buttonPanel);
		add.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				frame.getContentPane().removeAll();
				frame.add(createAdd(), BorderLayout.CENTER);
				frame.revalidate();
				frame.repaint();
				
			}
		});
		
		editordelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				frame.getContentPane().removeAll();
				frame.add(update(), BorderLayout.CENTER);
				frame.revalidate();
				frame.repaint();
				
			}
		});
		
		show.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				frame.getContentPane().removeAll();
				frame.add(showProduct(), BorderLayout.CENTER);
				frame.revalidate();
				frame.repaint();
		
			}
		});
	}
	public JPanel createAdd(){
		JPanel addPanel = new JPanel();
		addPanel.setLayout(new BoxLayout(addPanel, BoxLayout.Y_AXIS));
		addPanel.add(nameQ);
		addPanel.add(ProductName);
		addPanel.add(priceQ);
		addPanel.add(ProductPrice);
		addPanel.add(adder);
		adder.addActionListener(new ActionListener() {
		
			@Override
			public void actionPerformed(ActionEvent arg0) {
				service.addProduct(ProductName.getText(), Double.parseDouble(ProductPrice.getText()));

			}
		});
		return addPanel;
	}
	public JPanel update(){
		JPanel updatePanel = new JPanel();
		updatePanel.setLayout(new BoxLayout(updatePanel, BoxLayout.Y_AXIS));
		ArrayList<Product> prod = service.getAllProducts();
		JPanel display = new JPanel(); 
		display.setLayout(new BoxLayout(display, BoxLayout.Y_AXIS));
		for (int i = 0; i<prod.size(); i++){
			JPanel row = new JPanel();
			JLabel id = new JLabel(String.valueOf(prod.get(i).getId()));
			JLabel name = new JLabel(prod.get(i).getName() + " ");
			JLabel price = new JLabel(String.valueOf(prod.get(i).getPrice()));
			row.add(id);
			row.add(name);
			row.add(price);
			display.add(row);
		}
		updatePanel.add(display);
		updatePanel.add(edit);
		updatePanel.add(deleteProd);
		edit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				frame.getContentPane().removeAll();
				frame.add(edit(), BorderLayout.CENTER);
				frame.revalidate();
				frame.repaint();

			}
		});

		deleteProd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				frame.getContentPane().removeAll();
				frame.add(delete(), BorderLayout.CENTER);
				frame.revalidate();
				frame.repaint();
			}
		});
		return updatePanel;
	}

	public JPanel edit(){
		JPanel editPanel = new JPanel();
		editPanel.setLayout(new BoxLayout(editPanel, BoxLayout.Y_AXIS));
		editPanel.add(idQ);
		editPanel.add(ProductID);
		editPanel.add(nameQ);
		editPanel.add(ProductName);
		editPanel.add(priceQ);
		editPanel.add(ProductPrice);
		JButton submit = new JButton("Submit changes");
		editPanel.add(submit);
		submit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				service.updateProduct(Integer.parseInt(ProductID.getText()),ProductName.getText(),Double.parseDouble(ProductPrice.getText()));
				
			}
		});
		return editPanel;
		}

		public JPanel delete(){
			JPanel deletePanel = new JPanel();
			deletePanel.setLayout(new BoxLayout(deletePanel, BoxLayout.Y_AXIS));
			deletePanel.add(idQ);
			deletePanel.add(ProductID);
			JButton deleteProduct = new JButton ("Delete Product");
			deletePanel.add(deleteProduct);
			deleteProduct.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					service.deleteProduct(Integer.parseInt(ProductID.getText()));
				}
			});

			return deletePanel;
		}

		public JPanel showProduct(){
			JPanel showProd = new JPanel();
			ArrayList<Product> prod = service.getAllProducts();
			showProd.setLayout(new BoxLayout(showProd, BoxLayout.Y_AXIS));
			JPanel display = new JPanel(); 
			display.setLayout(new BoxLayout(display, BoxLayout.Y_AXIS));
			for (int i = 0; i<prod.size(); i++){
				JPanel row = new JPanel();
				JLabel id = new JLabel(String.valueOf(prod.get(i).getId()));
				JLabel name = new JLabel(prod.get(i).getName() + " ");
				JLabel price = new JLabel(String.valueOf(prod.get(i).getPrice()));
				row.add(id);
				row.add(name);
				row.add(price);
				display.add(row);
			}
			showProd.add(display);
			showProd.add(filterName);
			filterName.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					frame.getContentPane().removeAll();
					frame.add(filterofName(), BorderLayout.CENTER);
					frame.revalidate();
					frame.repaint();
				}
			});
			showProd.add(filterPrice);
			filterPrice.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					frame.getContentPane().removeAll();
					frame.add(filterofPrice(), BorderLayout.CENTER);
					frame.revalidate();
					frame.repaint();
				}

			});
			return showProd;
		}

		public JPanel filterofName(){
			JPanel filter = new JPanel();
			ArrayList<Product> prod = service.getAllProducts();
			ArrayList<Product> filteredprod = new ArrayList<>();
			filter.setLayout(new BoxLayout(filter, BoxLayout.Y_AXIS));
			filter.add(nameQ);
			filter.add(ProductName);
			JButton filt = new JButton("Filter");
			JPanel display = new JPanel(); 
			display.setLayout(new BoxLayout(display, BoxLayout.Y_AXIS));
			filter.add(filt);
			filt.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					display.removeAll(); 
			        filteredprod.clear();
					for (int i = 0; i<prod.size(); i++){
						if(prod.get(i).getName().contains(ProductName.getText())){
							filteredprod.add(prod.get(i));
						}
						
					}

					for (int i = 0; i<filteredprod.size(); i++){
						JPanel row = new JPanel();
						JLabel id = new JLabel(String.valueOf(filteredprod.get(i).getId()));
						JLabel name = new JLabel(filteredprod.get(i).getName() + " ");
						JLabel price = new JLabel(String.valueOf(filteredprod.get(i).getPrice()));
						row.add(id);
						row.add(name);
						row.add(price);
						display.add(row);
					}
					display.revalidate(); 
			        display.repaint();    
			    }
			});

			filter.add(display);
			return filter;

		}
		
		public JPanel filterofPrice(){
			JPanel filter = new JPanel();
			ArrayList<Product> prod = service.getAllProducts();
			ArrayList<Product> filteredprod = new ArrayList<>();
			filter.setLayout(new BoxLayout(filter, BoxLayout.Y_AXIS));
			filter.add(priceQ);
			filter.add(ProductPrice);
			JButton filt = new JButton("Filter");
			JPanel display = new JPanel(); 
			display.setLayout(new BoxLayout(display, BoxLayout.Y_AXIS));
			filter.add(filt);
			filt.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					display.removeAll(); 
			        filteredprod.clear();
					String res = getStringPart(ProductPrice);
					int num = getNumPart(ProductPrice);
					for (int i = 0; i<prod.size(); i++){
						if(res.equals("greater than") | res.equals(">")){
							if (prod.get(i).getPrice() > num){
								filteredprod.add(prod.get(i));
							}
						}
						if(res.equals("less than") | res.equals("<")){
							if (prod.get(i).getPrice() < num){
								filteredprod.add(prod.get(i));
							}
						}
						if(res.equals("greater than or equal") | res.equals(">=")){
							if (prod.get(i).getPrice() >= num){
								filteredprod.add(prod.get(i));
							}
						}
						if(res.equals("less than or equal") | res.equals("<=")){
							if (prod.get(i).getPrice() <= num){
								filteredprod.add(prod.get(i));
							}
						}
						if(res.equals("equals") | res.equals("=")){
							if (prod.get(i).getPrice() == num){
								filteredprod.add(prod.get(i));
							}
						}
					}

					for (int i = 0; i<filteredprod.size(); i++){
						JPanel row = new JPanel();
						JLabel id = new JLabel(String.valueOf(filteredprod.get(i).getId()));
						JLabel name = new JLabel(filteredprod.get(i).getName() + " ");
						JLabel price = new JLabel(String.valueOf(filteredprod.get(i).getPrice()));
						row.add(id);
						row.add(name);
						row.add(price);
						display.add(row);
					}
					display.revalidate(); 
			        display.repaint();  
				}
			});
			filter.add(display);
			return filter;

		}
		
		public String getStringPart(JTextField prod){
			String s =prod.getText();
			String result = "";
			int index = 0;
			for (int i = 0; i<s.length(); i++){
				if (s.charAt(i) == ' ' ){
					index = i;
				}
			}
			for (int i = 0; i < index; i++){
				result = result + s.charAt(i);
			}
			return result;
		}
		
		public int getNumPart(JTextField prod){
			String s =prod.getText();
			int num = 0;
			int index = 0;
			for (int i = 0; i<s.length(); i++){
				if (s.charAt(i) == ' ' ){
					index = i;
				}
			}
			num = Integer.parseInt(s.substring(index+1,s.length()));
			return num;
		}
		
		
		
		
		
			public static void main(String[] args) {
				SwingUtilities.invokeLater(() -> new StoreGUI());
			}
		}
