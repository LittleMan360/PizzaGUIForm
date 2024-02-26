import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.NumberFormat;


class PizzaGuiFrame extends JFrame {

    //Panels
    JPanel mainPnl;
    JPanel toppingPanel;
    JPanel crustPanel;
    JPanel sizePanel;
    JPanel orderPanel;
    JPanel buttonPanel;

    //Combo box
    JComboBox<String> SizeBox;

    //Checkboxes
    JCheckBox PepperoniCB;
    JCheckBox BaconCB;
    JCheckBox ExtraCheeseCB;
    JCheckBox SausageCB;
    JCheckBox HamCB;
    JCheckBox PineappleCB;

    //Radio buttons
    JRadioButton thinRB; //Thin crust
    JRadioButton regularRB; //Regular crust
    JRadioButton deepRB; //Deep Dish crust

    //Buttons
    JButton quitBtn;
    JButton orderBtn;
    JButton clearBtn;

    //Text area for order
    JTextArea OrderText;
    //Constructor
    public PizzaGuiFrame() {
        setTitle("Pizza Order");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        mainPnl = new JPanel(new BorderLayout());
        createCrustPanel();
        mainPnl.add(crustPanel, BorderLayout.WEST);
        CreateSizePanel();
        mainPnl.add(sizePanel, BorderLayout.NORTH);
        CreateToppingPanel();
        mainPnl.add(toppingPanel, BorderLayout.CENTER);
        CreateButtonPanel();
        mainPnl.add(buttonPanel, BorderLayout.SOUTH);
        CreateOrderPanel();
        mainPnl.add(orderPanel, BorderLayout.EAST);


        add(mainPnl);


        pack();
    }

    //Create crust panel
    void createCrustPanel() {
        crustPanel = new JPanel();
        crustPanel.setLayout(new BoxLayout(crustPanel, BoxLayout.Y_AXIS));
        crustPanel.setBorder(BorderFactory.createTitledBorder("Crust"));
        thinRB = new JRadioButton("Thin");
        regularRB = new JRadioButton("Regular");
        deepRB = new JRadioButton("Deep Dish");
        ButtonGroup crustGroup = new ButtonGroup();
        crustGroup.add(thinRB);
        crustGroup.add(regularRB);
        crustGroup.add(deepRB);
        crustPanel.add(thinRB);
        crustPanel.add(regularRB);
        crustPanel.add(deepRB);
    }

    //Create size panel
    void CreateSizePanel() {
        sizePanel = new JPanel();
        String[] size = {
                "Small",
                "Medium",
                "Large",
                "Super"
        };
        SizeBox = new JComboBox<>(size);

        sizePanel.add(SizeBox);
        sizePanel.setBorder(BorderFactory.createTitledBorder("Size"));
    }

    //Create topping panel
    void CreateToppingPanel() {
        toppingPanel = new JPanel();
        toppingPanel.setLayout(new BoxLayout(toppingPanel, BoxLayout.Y_AXIS));
        toppingPanel.setBorder(BorderFactory.createTitledBorder("Toppings"));
        PepperoniCB = new JCheckBox("Pepperoni");
        BaconCB = new JCheckBox("Bacon");
        ExtraCheeseCB = new JCheckBox("Extra Cheese");
        SausageCB = new JCheckBox("Sausage");
        HamCB = new JCheckBox("Ham");
        PineappleCB = new JCheckBox("Pineapple");
        toppingPanel.add(PepperoniCB);
        toppingPanel.add(BaconCB);
        toppingPanel.add(ExtraCheeseCB);
        toppingPanel.add(SausageCB);
        toppingPanel.add(HamCB);
        toppingPanel.add(PineappleCB);
    }

    //Create order panel
    void CreateOrderPanel() {
        orderPanel = new JPanel();
        OrderText = new JTextArea(20,40);
        OrderText.setEditable(false);

        orderPanel.add(OrderText);
        orderPanel.setBorder(BorderFactory.createTitledBorder("Order"));
    }



    //Create button panel
    void CreateButtonPanel() {
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        quitBtn = new JButton("Quit");
        orderBtn = new JButton("Order");
        clearBtn = new JButton("Clear");
        buttonPanel.add(quitBtn);
        buttonPanel.add(orderBtn);
        buttonPanel.add(clearBtn);

        //Quit button
        quitBtn.addActionListener(e -> System.exit(0));

        //Order button
        orderBtn.addActionListener((ActionEvent ae) -> {
            OrderText.setText("");
            double cost = 0;
            double tax;
            double total;
            NumberFormat formatter = NumberFormat.getCurrencyInstance();
            if (thinRB.isSelected()){OrderText.append("Thin\n");}
            if (regularRB.isSelected()){OrderText.append("Regular\n");}
            if (deepRB.isSelected()){OrderText.append("Deep-dish\n");}

            if (SizeBox.getSelectedIndex()==0){OrderText.append("Small           $8.00\n"); cost += 8;}
            if (SizeBox.getSelectedIndex()==1){OrderText.append("Medium          $12.00\n"); cost += 12;}
            if (SizeBox.getSelectedIndex()==2){OrderText.append("Large           $16.00\n"); cost += 16;}
            if (SizeBox.getSelectedIndex()==3){OrderText.append("Super           $20.00\n"); cost += 20;}

            if (PepperoniCB.isSelected()){OrderText.append("Peperoni        $1.00\n"); cost++;}
            if (SausageCB.isSelected()){OrderText.append("Sausage           $1.00\n"); cost++;}
            if (BaconCB.isSelected()){OrderText.append("Bacon           $1.00\n"); cost++;}
            if (ExtraCheeseCB.isSelected()){OrderText.append("Extra Cheese   $1.00\n"); cost++;}
            if (HamCB.isSelected()){OrderText.append("Ham         $1.00\n"); cost++;}
            if (PineappleCB.isSelected()){OrderText.append("Pineapple     $1.00\n"); cost++;}

            OrderText.append("---------------------------\n");
            tax = cost * .07;
            total = cost + tax;
            OrderText.append("Subtotal        " + formatter.format(cost) + "\n");
            OrderText.append("Tax             " + formatter.format(tax) + "\n");
            OrderText.append("---------------------------\n");
            OrderText.append("Total           " + formatter.format(total) + "\n");




        });
        //Clear button
        clearBtn.addActionListener((ActionEvent ae) -> {
            thinRB.setSelected(false);
            regularRB.setSelected(false);
            deepRB.setSelected(false);

            SizeBox.setSelectedItem(0);

            PepperoniCB.setSelected(false);
            SausageCB.setSelected(false);
            ExtraCheeseCB.setSelected(false);
            BaconCB.setSelected(false);
            HamCB.setSelected(false);
            PineappleCB.setSelected(false);

            OrderText.setText("");
        });


    }


}