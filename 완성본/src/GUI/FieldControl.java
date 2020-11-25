package GUI;


import ClimateCheck.*;
import Climate_function.*;
import org.json.simple.JSONArray;
import java.awt.event.*;
import java.awt.*;
import java.io.IOException;
import org.json.simple.parser.ParseException;

public class FieldControl  {
	private Frame mainFrame;
	private Label headerLabel;
	private Label bottomLabel;
	private Label statusLabel;
	private Panel controlPanel, buttonPanel;
	
	Climate ck = new Climate();
    ClimateMain ckm = new ClimateMain();
	Umbrella um = new Umbrella();
    Dresscode ds = new Dresscode();
    
	public FieldControl() {
		prepareGUI();
	}
	
	public static void main(String[] args) {
		FieldControl awtControlDemo = new FieldControl();
		awtControlDemo.showTextField();
	}
	
	public void prepareGUI() {
		mainFrame = new Frame("Climate_Check");
		mainFrame.setSize(500,500);
		mainFrame.setLayout(new GridLayout(4,1));
		mainFrame.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent windowEvent) {
				System.exit(0);
			}
		});
		
		headerLabel = new Label();
		bottomLabel = new Label();
		headerLabel.setAlignment(Label.CENTER);
		headerLabel.setText("EX) ��⵵ / ������ / ��â�� => �̷� ������ �Է����ּ���");
		bottomLabel.setAlignment(Label.CENTER);
		bottomLabel.setText("Ư������ ��� EX) ����Ư���� / ���α� / ��â�� => �̷� ������ �Է����ּ���"); 
		
		controlPanel = new Panel();
		controlPanel.setLayout(new FlowLayout());
		
		buttonPanel = new Panel();
		buttonPanel.setLayout(new FlowLayout());
		
		mainFrame.add(buttonPanel);
		mainFrame.add(controlPanel);
		mainFrame.add(headerLabel);
		mainFrame.add(bottomLabel);
			
	}
	
	public void showTextField()  {
		Label first = new Label("�� : ",Label.RIGHT);
		TextField DO = new TextField(13);
		
		Label second = new Label("�� : ",Label.RIGHT);
		TextField SI = new TextField(13);
		
		Label third = new Label("�� : ",Label.RIGHT);
		TextField DONG = new TextField(13);
		
		
		
		Button btn = new Button("����");
		Button btn1 = new Button("�� ����");
		Button btn2 = new Button("���");
		
		btn.setBounds(100, 500, 50, 70);
		
		btn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				String fir = DO.getText();
				String se = SI.getText();
				String th = DONG.getText();
				if(e.getActionCommand().equals("����")) {
					try {
						ckm.PrintClimate(fir, se, th);
						} catch (IOException | ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						}
				}				
			}
		});
		btn1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				String fir = DO.getText();
				String se = SI.getText();
				String th = DONG.getText();
				if(e.getActionCommand().equals("�� ����")) {
					try {
						ckm.PrintDressCode(fir, se, th);
						} catch (IOException | ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						}
				}				
			}
		});
		btn2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				String fir = DO.getText();
				String se = SI.getText();
				String th = DONG.getText();
				if(e.getActionCommand().equals("���")) {
					try {
						ckm.PrintUmbrella(fir, se, th);
						} catch (IOException | ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						}
				}				
			}
		});
		
		
		controlPanel.add(first);
		controlPanel.add(DO);
		controlPanel.add(second);
		controlPanel.add(SI);
		controlPanel.add(third);
		controlPanel.add(DONG);
		controlPanel.add(btn);
		controlPanel.add(btn1);
		controlPanel.add(btn2);
		mainFrame.setVisible(true);
		
	}
}
