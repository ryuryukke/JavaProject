import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;

public class NewBookSystem {
    private JTextField field;
    private DefaultListModel<String> tListModel;
    private DefaultListModel<String> fListmodel;
    private JList<String> trbook; //infomation for toread books
    private JLabel caution;
    private JLabel label1;
    private JLabel label2;
    
    // store book information(ToRead) <varied>
    public class ToReadButtonAction implements ActionListener {
	public void actionPerformed(ActionEvent e) {
	    String tempText = field.getText();
	    if(tempText.isEmpty())
		JOptionPane.showMessageDialog(trbook, "No Inputs!", "Error", JOptionPane.ERROR_MESSAGE);
	    else {
		NewRegister2 TR = new NewRegister2();
		TR.Register(tempText,"T");
		tListModel.addElement(tempText);
	    }
	}
    }

    //  store book information(Finished) <varied>
    public class FinishedButtonAction implements ActionListener {
	public void actionPerformed(ActionEvent e) {
	    String tempText = field.getText();
	    if(tempText.isEmpty())
		JOptionPane.showMessageDialog(trbook, "No Inputs!", "Error", JOptionPane.ERROR_MESSAGE);
	    NewRegister2 FI = new NewRegister2();
	    FI.Register(tempText,"F");
	}
    }
    // <varied>
    public class QuitButtonAction implements ActionListener {
	public void actionPerformed(ActionEvent e) {
	    System.exit(0);
	}
    }

    // initialize lists
    public class ClearButtonAction implements ActionListener {
	public void actionPerformed(ActionEvent e) {
	    NewRegister2 TR = new NewRegister2();
	    TR.Init();
	    tListModel.clear();
	}
    }

    // rearrange a list(interest)
    public class Re1ButtonAction implements ActionListener {
	public void actionPerformed(ActionEvent e) {
	    tListModel.clear();
	    NewRegister2 TR = new NewRegister2();
	    List<BookInfo> reList = new ArrayList<BookInfo>();
	    reList = Recommend.interest(TR.ListPass());
	    if(reList.size() == 0)
		JOptionPane.showMessageDialog(trbook, "No Inputs!", "Error", JOptionPane.ERROR_MESSAGE);
	    for(int i=0;i<reList.size();i++){
		String title = (reList.get(i)).i_title; String ISBN = (reList.get(i)).i_ISBN;
		String author = (reList.get(i)).i_author;  String publisher = (reList.get(i)).i_publisher;
		String pyear = (reList.get(i)).i_pyear;
		tListModel.addElement(title+" "+ISBN+" "+author+" "+publisher+" "+pyear);
	    }
	}
    }

    // rearrange a list(popularity)
    public class Re2ButtonAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            tListModel.clear();
            NewRegister2 TR = new NewRegister2();
            List<BookInfo> reList = new ArrayList<BookInfo>();
            reList = Recommend.popularity(TR.ListPass());
	    //if(reList.size() == 0)
            //    JOptionPane.showMessageDialog(trbook, "No Inputs!", "Error", JOptionPane.ERROR_MESSAGE);
            for(int i=0;i<reList.size();i++){
                String title = (reList.get(i)).i_title; String ISBN = (reList.get(i)).i_ISBN;
                String author = (reList.get(i)).i_author;  String publisher = (reList.get(i)).i_publisher;
                String pyear = (reList.get(i)).i_pyear;
                tListModel.addElement(title+" "+ISBN+" "+author+" "+publisher+" "+pyear);
            }
        }
    }

    public Component createComponents() {
	field = new JTextField("Please input a book information.");

	caution = new JLabel("title / ISBN / autor / publisher / pyear");
	caution.setAlignmentX(Component.CENTER_ALIGNMENT);
	label1 = new JLabel("Register Option");
	label1.setAlignmentX(Component.CENTER_ALIGNMENT);
	label2 = new JLabel("Sorting Option");
	label2.setAlignmentX(Component.CENTER_ALIGNMENT);


	tListModel = new DefaultListModel<String>();
	fListmodel = new DefaultListModel<String>();

	trbook = new JList<String>(tListModel);
	trbook.setVisibleRowCount(10);
	trbook.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	JScrollPane scrollPane1 = new JScrollPane(trbook);
	scrollPane1.createVerticalScrollBar();
	scrollPane1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);


	JButton tRegButton = new JButton("ToRead");
	ToReadButtonAction tRegButtonListener = new ToReadButtonAction();
	tRegButton.addActionListener( tRegButtonListener );

	tRegButton.setAlignmentX(Component.CENTER_ALIGNMENT);

	JButton fRegButton = new JButton("Finished");
	FinishedButtonAction fRegButtonListener = new FinishedButtonAction();
	fRegButton.addActionListener( fRegButtonListener );

	fRegButton.setAlignmentX(Component.CENTER_ALIGNMENT);


	JButton quitButton = new JButton("Quit");
	QuitButtonAction quitButtonListener = new QuitButtonAction();
	quitButton.addActionListener( quitButtonListener );

	JButton clearButton = new JButton("Clear");
        ClearButtonAction clearButtonListener = new ClearButtonAction();
        clearButton.addActionListener( clearButtonListener );

	JButton Re1Button = new JButton("interest");
	Re1ButtonAction Re1ButtonListener = new Re1ButtonAction();
	Re1Button.addActionListener( Re1ButtonListener );
	Re1Button.setAlignmentX(Component.CENTER_ALIGNMENT);

	JButton Re2Button = new JButton("popularity");
	Re2ButtonAction Re2ButtonListener = new Re2ButtonAction();
	Re2Button.addActionListener( Re2ButtonListener );
	Re2Button.setAlignmentX(Component.CENTER_ALIGNMENT);

	JPanel subPane1 = new JPanel();
	subPane1.setLayout(new BoxLayout(subPane1, BoxLayout.X_AXIS));
	subPane1.add(Box.createRigidArea(new Dimension(10, 10)));
	subPane1.add(clearButton);
	subPane1.add(Box.createRigidArea(new Dimension(20, 10)));
	subPane1.add(quitButton);

	JPanel subPane2 = new JPanel();
	subPane2.setLayout(new BoxLayout(subPane2, BoxLayout.X_AXIS));
	subPane2.add(Box.createRigidArea(new Dimension(10, 20)));
	subPane2.add(Re1Button);
	subPane2.add(Box.createRigidArea(new Dimension(0, 20)));
	subPane2.add(Re2Button);

	JPanel subPane3 = new JPanel();
	subPane3.setLayout(new BoxLayout(subPane3, BoxLayout.X_AXIS));
	subPane2.add(Box.createRigidArea(new Dimension(10, 20)));
	subPane3.add(tRegButton);
	subPane3.add(Box.createRigidArea(new Dimension(0, 20)));
	subPane3.add(fRegButton);



	/* create main panel*/
	JPanel mainPane = new JPanel();
	mainPane.setBorder(BorderFactory.createEmptyBorder( 30, 30, 30, 30 ));
	mainPane.setLayout(new BoxLayout(mainPane, BoxLayout.Y_AXIS));
	mainPane.add(caution);
	mainPane.add(field);

	mainPane.add(Box.createRigidArea(new Dimension(0, 20)));
	mainPane.add(label1);
	mainPane.add(Box.createRigidArea(new Dimension(0, 10)));
	mainPane.add(subPane3);
	mainPane.add(Box.createRigidArea(new Dimension(0, 30)));
	mainPane.add(label2);
	mainPane.add(Box.createRigidArea(new Dimension(0, 10)));
	mainPane.add(subPane2);
	mainPane.add(Box.createRigidArea(new Dimension(0, 30)));
	mainPane.add(scrollPane1);
	mainPane.add(Box.createRigidArea(new Dimension(0, 30)));
	mainPane.add(subPane1);

	return mainPane;
    }

    public static void main(String[] args) {
	JFrame frame = new JFrame("Book System");
	NewBookSystem app = new NewBookSystem();
	Component contents = app.createComponents();
	frame.getContentPane().add(contents, BorderLayout.CENTER);

	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.pack();
	frame.setVisible(true);
    }
}
