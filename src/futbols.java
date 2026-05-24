import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class futbols extends JFrame {

    private JComboBox<speletajs> playerCombo;
    private JComboBox<String> posCombo;
    private JButton btnAdd;
    private JButton btnDelete;
    private JButton btnInfo;
    private JList<String> teamList;
    private DefaultListModel<String> listModel;
    private JTextArea infoArea;

    private ArrayList<speletajs> availablePlayers;

    private Color backgroundColor = new Color(240, 242, 245);
    private Color panelColor = new Color(255, 255, 255);
    private Color textColor = new Color(33, 37, 41);
    private Color buttonColor = new Color(0, 123, 255);
    private Color deleteButtonColor = new Color(220, 53, 69);

    public futbols() {
        setTitle("Liverpool FC Squad Manager");
        setSize(750, 530);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        availablePlayers = new ArrayList<>();
        availablePlayers.add(new uzbrucejs("Mohamed Salah", 34, 175, 90, true));
        availablePlayers.add(new uzbrucejs("Darwin Nunez", 27, 187, 83, true));
        availablePlayers.add(new uzbrucejs("Luis Diaz", 30, 180, 85, true));
        availablePlayers.add(new uzbrucejs("Diogo Jota", 30, 178, 84, true));
        availablePlayers.add(new uzbrucejs("Cody Gakpo", 28, 189, 84, true));
        availablePlayers.add(new pussargs("Alexis Mac Allister", 28, 176, 87, true, true));
        availablePlayers.add(new pussargs("Dominik Szoboszlai", 26, 186, 86, true, true));
        availablePlayers.add(new pussargs("Curtis Jones", 26, 185, 81, true, true));
        availablePlayers.add(new pussargs("Ryan Gravenberch", 25, 190, 82, true, true));
        availablePlayers.add(new pussargs("Wataru Endo", 34, 178, 79, true, true));
        availablePlayers.add(new aizsargs("Virgil van Dijk", 35, 195, 88, true));
        availablePlayers.add(new aizsargs("Trent Alexander-Arnold", 28, 180, 88, true));
        availablePlayers.add(new aizsargs("Andrew Robertson", 33, 178, 85, true));
        availablePlayers.add(new aizsargs("Ibrahima Konate", 28, 194, 85, true));
        availablePlayers.add(new aizsargs("Joe Gomez", 30, 188, 80, true));
        availablePlayers.add(new vartsargs("Alisson Becker", 34, 191, 89, true));
        availablePlayers.add(new vartsargs("Caoimhin Kelleher", 28, 188, 81, true));

        JPanel mainPanel = new JPanel(new BorderLayout(12, 12));
        mainPanel.setBackground(backgroundColor);
        mainPanel.setBorder(new EmptyBorder(12, 12, 12, 12));

        JPanel topPanel = new JPanel(new GridLayout(1, 2, 12, 0));
        topPanel.setOpaque(false);

        JPanel leftSelectPanel = new JPanel(new GridLayout(2, 1, 0, 8));
        leftSelectPanel.setOpaque(false);

        playerCombo = new JComboBox<>();
        posCombo = new JComboBox<>(new String[]{
                "Uzbrucejs",
                "Pussargs",
                "Aizsargs",
                "Vartsargs"
        });

        leftSelectPanel.add(playerCombo);
        leftSelectPanel.add(posCombo);
        topPanel.add(leftSelectPanel);

        JPanel rightButtonPanel = new JPanel(new GridLayout(3, 1, 0, 6));
        rightButtonPanel.setOpaque(false);

        btnAdd = new JButton("Pievienot sastavam");
        btnAdd.setBackground(buttonColor);
        btnAdd.setForeground(Color.WHITE);
        btnAdd.setFocusPainted(false);

        btnInfo = new JButton("Get info");
        btnInfo.setBackground(buttonColor);
        btnInfo.setForeground(Color.WHITE);
        btnInfo.setFocusPainted(false);

        btnDelete = new JButton("Dzest no sastava");
        btnDelete.setBackground(deleteButtonColor);
        btnDelete.setForeground(Color.WHITE);
        btnDelete.setFocusPainted(false);

        rightButtonPanel.add(btnAdd);
        rightButtonPanel.add(btnInfo);
        rightButtonPanel.add(btnDelete);
        topPanel.add(rightButtonPanel);

        JPanel infoPanel = new JPanel(new BorderLayout());
        infoPanel.setBackground(panelColor);
        infoPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY), "Informacija",
                TitledBorder.LEFT, TitledBorder.TOP, new Font("Arial", Font.PLAIN, 12), textColor));

        infoArea = new JTextArea(4, 20);
        infoArea.setEditable(false);
        infoArea.setBackground(panelColor);
        infoArea.setForeground(textColor);
        infoArea.setFont(new Font("Arial", Font.PLAIN, 13));
        infoArea.setMargin(new Insets(6, 6, 6, 6));
        infoPanel.add(infoArea, BorderLayout.CENTER);

        JPanel controlContainer = new JPanel(new BorderLayout(0, 12));
        controlContainer.setOpaque(false);
        controlContainer.add(topPanel, BorderLayout.NORTH);
        controlContainer.add(infoPanel, BorderLayout.CENTER);

        mainPanel.add(controlContainer, BorderLayout.NORTH);

        listModel = new DefaultListModel<>();
        teamList = new JList<>(listModel);
        teamList.setBackground(panelColor);
        teamList.setForeground(textColor);
        teamList.setFont(new Font("Arial", Font.PLAIN, 14));

        JScrollPane scrollPane = new JScrollPane(teamList);
        scrollPane.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY), "Komandas sastavs",
                TitledBorder.LEFT, TitledBorder.TOP, new Font("Arial", Font.PLAIN, 12), textColor));
        scrollPane.getViewport().setBackground(panelColor);

        mainPanel.add(scrollPane, BorderLayout.CENTER);
        add(mainPanel);

        loadFromFile();
        refillPlayerCombo();

        btnInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                speletajs p = (speletajs) playerCombo.getSelectedItem();
                String position = (String) posCombo.getSelectedItem();
                
                if (p != null && position != null) {
                    String info = "Vards: " + p.name + "  |  Vecums: " + p.age + "  |  Augums: " + p.height + " cm  |  Reitings: " + p.rating + "\n";
                    String action = "Darbiba: ";
                    
                    if (position.equals("Uzbrucejs")) {
                        if (p instanceof uzbrucejs) {
                            uzbrucejs u = (uzbrucejs) p;
                            if (u.score) action += u.name + " iesit vartus!";
                            else action += u.name + " aizsit garam.";
                        } else {
                            action += p.name + " nespej spelet uzbrukuma un zaude bumbu.";
                        }
                    } else if (position.equals("Pussargs")) {
                        if (p instanceof pussargs) {
                            pussargs m = (pussargs) p;
                            if (m.score) action += m.name + " atdod rezultativu piespeli!";
                            else action += m.name + " zaude bumbu viduszona.";
                        } else {
                            action += p.name + " neprot organizet uzbrukumus pussarga pozicija.";
                        }
                    } else if (position.equals("Aizsargs")) {
                        if (p instanceof aizsargs) {
                            aizsargs d = (aizsargs) p;
                            if (d.block) action += d.name + " drosi bloke bumbu!";
                            else action += d.name + " nevareja apturet pretinieku.";
                        } else {
                            action += p.name + " kludas aizsardziba, pretinieks iet garam.";
                        }
                    } else if (position.equals("Vartsargs")) {
                        if (p instanceof vartsargs) {
                            vartsargs gk = (vartsargs) p;
                            if (gk.defend) action += gk.name + " kver bumbu un glabj vartus!";
                            else action += gk.name + " ielaiz vartus.";
                        } else {
                            action += p.name + " neprot stret vārtos un ielaiž vieglus vārtus.";
                        }
                    }
                    
                    infoArea.setText(info + action);
                }
            }
        });

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                speletajs selected = (speletajs) playerCombo.getSelectedItem();
                String position = (String) posCombo.getSelectedItem();

                if (selected != null && position != null) {
                    listModel.addElement(position + " -> " + selected.name);
                    availablePlayers.remove(selected);
                    refillPlayerCombo();
                    infoArea.setText("");
                    saveToFile();
                }
            }
        });

        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = teamList.getSelectedIndex();
                if (selectedIndex != -1) {
                    String itemText = listModel.get(selectedIndex);
                    String playerName = itemText.substring(itemText.indexOf(" -> ") + 4);

                    speletajs[] allPlayers = {
                            new uzbrucejs("Mohamed Salah", 34, 175, 90, true),
                            new uzbrucejs("Darwin Nunez", 27, 187, 83, true),
                            new uzbrucejs("Luis Diaz", 30, 180, 85, true),
                            new uzbrucejs("Diogo Jota", 30, 178, 84, true),
                            new uzbrucejs("Cody Gakpo", 28, 189, 84, true),
                            new pussargs("Alexis Mac Allister", 28, 176, 87, true, true),
                            new pussargs("Dominik Szoboszlai", 26, 186, 86, true, true),
                            new pussargs("Curtis Jones", 26, 185, 81, true, true),
                            new pussargs("Ryan Gravenberch", 25, 190, 82, true, true),
                            new pussargs("Wataru Endo", 34, 178, 79, true, true),
                            new aizsargs("Virgil van Dijk", 35, 195, 88, true),
                            new aizsargs("Trent Alexander-Arnold", 28, 180, 88, true),
                            new aizsargs("Andrew Robertson", 33, 178, 85, true),
                            new aizsargs("Ibrahima Konate", 28, 194, 85, true),
                            new aizsargs("Joe Gomez", 30, 188, 80, true),
                            new vartsargs("Alisson Becker", 34, 191, 89, true),
                            new vartsargs("Caoimhin Kelleher", 28, 188, 81, true)
                    };

                    for (speletajs p : allPlayers) {
                        if (p.name.equals(playerName)) {
                            availablePlayers.add(p);
                            break;
                        }
                    }

                    listModel.remove(selectedIndex);
                    refillPlayerCombo();
                    infoArea.setText("");
                    saveToFile();
                }
            }
        });
    }

    private void refillPlayerCombo() {
        playerCombo.removeAllItems();
        for (speletajs p : availablePlayers) {
            playerCombo.addItem(p);
        }
        if (playerCombo.getItemCount() == 0) {
            infoArea.setText("Visi speletaji ir izveleti.");
        }
    }

    private void saveToFile() {
        try (FileWriter fw = new FileWriter("liverpool_sastavs.txt");
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write("LIVERPOOL FC PAŠREIZĒJAIS SASTĀVS");
            bw.newLine();
            bw.write("---------------------------------");
            bw.newLine();
            bw.write("POZĪCIJA | SPĒLĒTĀJA VĀRDS");
            bw.newLine();
            bw.write("---------------------------------");
            bw.newLine();
            for (int i = 0; i < listModel.size(); i++) {
                String item = listModel.get(i);
                String formatted = item.replace(" -> ", " | ");
                bw.write(formatted);
                bw.newLine();
            }
            bw.write("---------------------------------");
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadFromFile() {
        File file = new File("liverpool_sastavs.txt");
        if (!file.exists()) {
            return;
        }
        try (FileReader fr = new FileReader(file);
             BufferedReader br = new BufferedReader(fr)) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.contains("|") || line.contains("POZĪCIJA")) {
                    continue;
                }
                String[] parts = line.split("\\|");
                if (parts.length < 2) {
                    continue;
                }
                String position = parts[0].trim();
                String playerName = parts[1].trim();
                
                listModel.addElement(position + " -> " + playerName);
                
                speletajs toRemove = null;
                for (speletajs p : availablePlayers) {
                    if (p.name.equals(playerName)) {
                        toRemove = p;
                        break;
                    }
                }
                if (toRemove != null) {
                    availablePlayers.remove(toRemove);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new futbols().setVisible(true);
            }
        });
    }
}
