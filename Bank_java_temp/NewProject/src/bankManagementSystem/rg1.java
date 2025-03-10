package bankManagementSystem;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
import java.sql.*;
import com.toedter.calendar.JDateChooser;

public class rg1 extends JFrame implements ActionListener {
    int random;
    JTextField nameText, fnameText, eText, add1Text, pcText;
    JButton next;
    JRadioButton male, female, other, married, unmarried, other1;
    JDateChooser dateChooser;
    JComboBox<String> cityComboBox;
    JComboBox<String> stateComboBox;

    rg1() {
        setTitle("Global Bank Management System");
        setLayout(null);

        // Random ran = new Random();
        // random = Math.abs(ran.nextInt(9999));
        Random ran = new Random();
        random = 1000 + ran.nextInt(9000);

        JLabel formno = new JLabel("REGISTRATION FORM No. " + random);
        formno.setFont(new Font("Arial", Font.BOLD, 38));
        formno.setBounds(140, 20, 600, 40);
        add(formno);

        JLabel perDetail = new JLabel("Page 1 : Personal Details");
        perDetail.setFont(new Font("Arial", Font.BOLD, 22));
        perDetail.setBounds(290, 80, 400, 30);
        add(perDetail);

        JLabel name = new JLabel("Name : ");
        name.setFont(new Font("Arial", Font.BOLD, 20));
        name.setBounds(100, 140, 100, 30);
        add(name);

        nameText = new JTextField();
        nameText.setFont(new Font("Arial", Font.BOLD, 15));
        nameText.setBounds(300, 140, 400, 30);
        add(nameText);

        // Validation using Regex when focus is lost
        nameText.addFocusListener(new FocusAdapter() {
            public void focusLost(FocusEvent e) {
                String nameInput = nameText.getText().trim();
                if (!nameInput.matches("^[a-zA-Z ]{3,50}$")) {
                    JOptionPane.showMessageDialog(null,
                            "Invalid Name! Only letters and spaces allowed .", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    nameText.setText(""); // Clear field if invalid
                }
            }
        });

        JLabel fname = new JLabel("Father's Name : ");
        fname.setFont(new Font("Arial", Font.BOLD, 20));
        fname.setBounds(100, 190, 200, 30);
        add(fname);

        fnameText = new JTextField();
        fnameText.setFont(new Font("Arial", Font.BOLD, 15));
        fnameText.setBounds(300, 190, 400, 30);
        add(fnameText);

        nameText.addFocusListener(new FocusAdapter() {
            public void focusLost(FocusEvent e) {
                String nameInput = nameText.getText().trim();
                if (!nameInput.matches("^[a-zA-Z ]{3,50}$")) {
                    JOptionPane.showMessageDialog(null,
                            "Invalid Name! Only letters and spaces allowed .", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    nameText.setText(""); 
                }
            }
        });

        JLabel dob = new JLabel("Date of Birth : ");
        dob.setFont(new Font("Arial", Font.BOLD, 20));
        dob.setBounds(100, 240, 200, 30);
        add(dob);

        dateChooser = new JDateChooser();
        dateChooser.setBounds(300, 240, 400, 30);
        add(dateChooser);

        JLabel gen = new JLabel("Gender : ");
        gen.setFont(new Font("Arial", Font.BOLD, 20));
        gen.setBounds(100, 290, 200, 30);
        add(gen);

        male = new JRadioButton("Male");
        male.setBounds(300, 290, 60, 30);
        male.setBackground(new Color(152, 251, 152));
        add(male);

        female = new JRadioButton("Female");
        female.setBounds(400, 290, 120, 30);
        female.setBackground(new Color(152, 251, 152));
        add(female);

        other = new JRadioButton("Other");
        other.setBounds(520, 290, 120, 30);
        other.setBackground(new Color(152, 251, 152));
        add(other);

        ButtonGroup bg = new ButtonGroup();
        bg.add(male);
        bg.add(female);
        bg.add(other);

        JLabel email = new JLabel("E-mail Address : ");
        email.setFont(new Font("Arial", Font.BOLD, 20));
        email.setBounds(100, 340, 200, 30);
        add(email);

        eText = new JTextField();
        eText.setFont(new Font("Arial", Font.BOLD, 15));
        eText.setBounds(300, 340, 400, 30);
        add(eText);

        eText.addFocusListener(new FocusAdapter() {
            public void focusLost(FocusEvent e) {
                String emailInput = eText.getText().trim();
                if (!emailInput.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")) {
                    JOptionPane.showMessageDialog(null,
                            "Invalid E-mail Address! Please include a valid domain (e.g., .com) in your email.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                    eText.setText(""); 
                }
            }
        });

        JLabel marital = new JLabel("Marital Status : ");
        marital.setFont(new Font("Arial", Font.BOLD, 20));
        marital.setBounds(100, 390, 200, 30);
        add(marital);

        married = new JRadioButton("Married");
        married.setBounds(300, 390, 100, 30);
        married.setBackground(new Color(152, 251, 152));
        add(married);

        unmarried = new JRadioButton("Unmarried");
        unmarried.setBounds(400, 390, 120, 30);
        unmarried.setBackground(new Color(152, 251, 152));
        add(unmarried);

        other1 = new JRadioButton("Other");
        other1.setBounds(520, 390, 100, 30);
        other1.setBackground(new Color(152, 251, 152));
        add(other1);

        ButtonGroup mg = new ButtonGroup();
        mg.add(married);
        mg.add(unmarried);
        mg.add(other1);

        JLabel add1 = new JLabel("Address : ");
        add1.setFont(new Font("Arial", Font.BOLD, 20));
        add1.setBounds(100, 440, 200, 30);
        add(add1);

        add1Text = new JTextField();
        add1Text.setFont(new Font("Arial", Font.BOLD, 15));
        add1Text.setBounds(300, 440, 400, 30);
        add(add1Text);

        JLabel city = new JLabel("City : ");
        city.setFont(new Font("Arial", Font.BOLD, 20));
        city.setBounds(100, 490, 200, 30);
        add(city);

        String[] cities = { "Adoni", "Agartala", "Agra", "Ahmedabad", "Ahmednagar", "Aizawl", "Ajmer", "Alappuzha",
                "Aligarh", "Allahabad", "Ambala", "Ambarnath", "Ambattur", "Amravati", "Amritsar", "Anand", "Anantapur",
                "Arrah", "Aurangabad", "Bally", "Bangalore", "Bareilly", "Bathinda", "Begusarai", "Belgaum", "Bellary",
                "Bharatpur", "Bhatpara", "Bhavnagar", "Bhilai", "Bhilwara", "Bhimavaram", "Bhind", "Bhiwandi",
                "Bhiwani", "Bhopal", "Bhubaneswar", "Bhuj", "Bhusawal", "Bidar", "Bidhannagar", "Bijapur", "Bikaner",
                "Bilaspur", "Bokaro", "Brahmapur", "Bulandshahr", "Burhanpur", "Chandigarh", "Chandrapur", "Chennai",
                "Chinsurah", "Chittoor", "Coimbatore", "Cuttack", "Darbhanga", "Davanagere", "Dehradun", "Deoghar",
                "Dewas", "Dhanbad", "Dibrugarh", "Dimapur", "Dindigul", "Durg", "Durgapur", "Eluru", "Etawah",
                "Faridabad", "Farrukhabad", "Fatehpur", "Firozabad", "Gandhidham", "Gandhinagar", "Gaya", "Ghaziabad",
                "Giridih", "Gopalpur", "Gorakhpur", "Gulbarga", "Guna", "Guntur", "Gurgaon", "Guwahati", "Gwalior",
                "Hajipur", "Haldia", "Hapur", "Haridwar", "Hazaribag", "Hindupur", "Howrah", "Hubli-Dharwad",
                "Hyderabad", "Ichalkaranji", "Imphal", "Indore", "Jabalpur", "Jagdalpur", "Jaipur", "Jalandhar",
                "Jalgaon", "Jalna", "Jammu", "Jamnagar", "Jamshedpur", "Jhansi", "Jodhpur", "Jorhat", "Junagadh",
                "Kadapa", "Kakinada", "Kalyan-Dombivli", "Kamarhati", "Kanpur", "Karawal Nagar", "Karimnagar", "Karnal",
                "Katihar", "Katni", "Kharagpur", "Khora, Ghaziabad", "Khammam", "Kochi", "Kollam", "Kolhapur",
                "Kolkata", "Kota", "Kottayam", "Kozhikode", "Kulti", "Kurnool", "Latur", "Loni", "Lucknow", "Ludhiana",
                "Machilipatnam", "Madhyamgram", "Madurai", "Mahbubnagar", "Maheshtala", "Malegaon", "Malda",
                "Mangalore", "Mango", "Mathura", "Meerut", "Mehsana", "Mirzapur", "Moradabad", "Morbi", "Morena",
                "Muzaffarnagar", "Muzaffarpur", "Munger", "Mumbai", "Mysore", "Nadiad", "Nagaon", "Nagpur", "Naihati",
                "Nanded", "Nandyal", "Nangloi Jat", "Nashik", "Navsari", "Navi Mumbai", "New Delhi", "Nizamabad",
                "Noida", "North Dumdum", "Ongole", "Orai", "Panihati", "Panipat", "Panvel", "Parbhani", "Patiala",
                "Patna", "Pimpri-Chinchwad", "Pondicherry", "Proddatur", "Pune", "Purnia", "Raebareli", "Raichur",
                "Raiganj", "Raipur", "Rajkot", "Rajpur Sonarpur", "Ramagundam", "Rampur", "Ranchi", "Ratlam",
                "Raurkela Industrial Township", "Rewa", "Rohtak", "Rourkela", "Saharanpur", "Sagar", "Saharanpur",
                "Salem", "Sambalpur", "Sangli-Miraj & Kupwad", "Satara", "Satna", "Secunderabad", "Serampore", "Shimla",
                "Shimoga", "Shikohabad", "Shivpuri", "Siliguri", "Singrauli", "Sirsa", "Srikakulam", "Srinagar",
                "Sultan Pur Majra", "Surat", "Surendranagar Dudhrej", "Tenali", "Thane", "Thanjavur",
                "Thiruvananthapuram", "Thoothukudi", "Thrissur", "Tiruchirappalli", "Tirunelveli", "Tirupati",
                "Tiruvottiyur", "Tumkur", "Udaipur", "Udupi", "Ujjain", "Ulhasnagar", "Uluberia", "Unnao",
                "Uzhavarkarai", "Vadodara", "Varanasi", "Vasai-Virar", "Vellore", "Vijayawada", "Vijayanagaram",
                "Visakhapatnam", "Warangal", "Yamunanagar" };
        cityComboBox = new JComboBox<>(cities);
        cityComboBox.setFont(new Font("Arial", Font.BOLD, 15));
        cityComboBox.setBounds(300, 490, 400, 30);
        cityComboBox.setEditable(true);
        JTextField cityEditor = (JTextField) cityComboBox.getEditor().getEditorComponent();
        cityEditor.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                String text = cityEditor.getText();
                cityComboBox.removeAllItems();
                for (String city : cities) {
                    if (city.toLowerCase().startsWith(text.toLowerCase())) {
                        cityComboBox.addItem(city);
                    }
                }
                cityEditor.setText(text);
                cityComboBox.showPopup();
            }
        });
        add(cityComboBox);

        JLabel st = new JLabel("State : ");
        st.setFont(new Font("Arial", Font.BOLD, 20));
        st.setBounds(100, 540, 200, 30);
        add(st);

        String[] states = { "Andhra Pradesh", "Arunachal Pradesh", "Assam", "Bihar", "Chhattisgarh", "Goa", "Gujarat",
                "Haryana", "Himachal Pradesh", "Jharkhand", "Karnataka", "Kerala", "Madhya Pradesh", "Maharashtra",
                "Manipur", "Meghalaya", "Mizoram", "Nagaland", "Odisha", "Punjab", "Rajasthan", "Sikkim", "Tamil Nadu",
                "Telangana", "Tripura", "Uttar Pradesh", "Uttarakhand", "West Bengal" };
        stateComboBox = new JComboBox<>(states);
        stateComboBox.setFont(new Font("Arial", Font.BOLD, 15));
        stateComboBox.setBounds(300, 540, 400, 30);
        stateComboBox.setEditable(true);
        JTextField stateEditor = (JTextField) stateComboBox.getEditor().getEditorComponent();
        stateEditor.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                String text = stateEditor.getText();
                stateComboBox.removeAllItems();
                for (String state : states) {
                    if (state.toLowerCase().startsWith(text.toLowerCase())) {
                        stateComboBox.addItem(state);
                    }
                }
                stateEditor.setText(text);
                stateComboBox.showPopup();
            }
        });
        add(stateComboBox);

        JLabel pc = new JLabel("Pin Code : ");
        pc.setFont(new Font("Arial", Font.BOLD, 20));
        pc.setBounds(100, 590, 200, 30);
        add(pc);

        pcText = new JTextField();
        pcText.setFont(new Font("Arial", Font.BOLD, 15));
        pcText.setBounds(300, 590, 400, 30);
        add(pcText);

        pcText.addFocusListener(new FocusAdapter() {
            public void focusLost(FocusEvent e) {
                String pinCodeInput = pcText.getText().trim();
                if (!pinCodeInput.matches("^[1-9][0-9]{5}$")) {
                    JOptionPane.showMessageDialog(null,
                            "Invalid Pin Code! Please enter a valid 6-digit pin code.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    pcText.setText(""); // Clear field if invalid
                }
            }
        });

        next = new JButton("Next");
        next.setBackground(Color.BLACK);
        next.setForeground(Color.WHITE);
        next.setFont(new Font("Raleway", Font.BOLD, 15));
        next.setBounds(600, 650, 100, 30);
        next.addActionListener(this);
        add(next);

        getContentPane().setBackground(new Color(152, 251, 152)); // Light Blue
        setSize(850, 750);
        setLocation(330, 10);
        setResizable(false);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        String formno = "" + random;
        String name = nameText.getText();
        String fname = fnameText.getText();
        String dob = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
        String gender = null;
        if (male.isSelected()) {
            gender = "Male";
        } else if (female.isSelected()) {
            gender = "Female";
        } else if (other.isSelected()) {
            gender = "Other";
        }

        String email = eText.getText();

        String marital = null;
        if (married.isSelected()) {
            marital = "Married";
        } else if (unmarried.isSelected()) {
            marital = "Unmarried";
        } else if (other1.isSelected()) {
            marital = "Other";
        }

        String add1 = add1Text.getText();
        String city = (String) cityComboBox.getSelectedItem();
        String state = (String) stateComboBox.getSelectedItem();
        String pincode = pcText.getText();

        try {
            if (name.equals("")) {
                JOptionPane.showMessageDialog(null, "Name is Required***");
            } else if (fname.equals("")) {
                JOptionPane.showMessageDialog(null, "Father's Name is Required***");
            } else if (dob.equals("")) {
                JOptionPane.showMessageDialog(null, "Date of Birth is Required***");
            } else if (pincode.equals("")) {
                JOptionPane.showMessageDialog(null, "Pincode is Required***");
            } else {
                Conn c = new Conn();
                String query = "INSERT INTO rg1 (formno, name, fname, dob, gender, email, marital, add1, city, state, pincode) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement pstmt = c.c.prepareStatement(query);
                pstmt.setString(1, formno);
                pstmt.setString(2, name);
                pstmt.setString(3, fname);
                pstmt.setString(4, dob);
                pstmt.setString(5, gender);
                pstmt.setString(6, email);
                pstmt.setString(7, marital);
                pstmt.setString(8, add1);
                pstmt.setString(9, city);
                pstmt.setString(10, state);
                pstmt.setString(11, pincode);

                pstmt.executeUpdate();
                pstmt.close();

                JOptionPane.showMessageDialog(null, "Details Submitted Successfully!");
                setVisible(false);
                new rg2(formno).setVisible(true);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // public static void main(String[] args) {
    //     new rg1();
    // }

}
