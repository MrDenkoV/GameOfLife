package gol.vis;

import gol.log.Map;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Visualisation{
    private Timer timer;
    private boolean isTimerRunning;
    private Map map;
    private Position[][] playground;

    private JPanel buttonPanel;
    private JPanel rightPanel;
    private JPanel textPanel;
    private JPanel playgroundPanel;
    private JButton startStopButton;
    private JButton resetButton;
    private JButton exitButton;
    private JButton submitButton;
    private JTextField rules;


    public Visualisation(Map map){
        this.timer=new Timer(100, timerAction);
        this.isTimerRunning=false;
        this.map=map;
        this.playground=new Position[Map.width][Map.height];
    }

    private ActionListener timerAction = new ActionListener()
    {
        public void actionPerformed(ActionEvent ae)
        {
//            return;
            map.nextStep(playground);
            playgroundPanel.revalidate();
            playgroundPanel.repaint();
        }
    };

    public void visualise(){
        JFrame frame=new JFrame("Game of Life");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(50,50);
        frame.setSize(1300, 900);
        frame.setBackground(Color.DARK_GRAY);
        frame.setResizable(true);


        frame.add(makePlaygroundPanel(), BorderLayout.CENTER);
        frame.add(makeRightPanel(), BorderLayout.LINE_END);
        frame.setVisible(true);
    }


    private JPanel makePlaygroundPanel(){
        playgroundPanel = new JPanel();
        playgroundPanel.setLayout(new GridLayout(map.height, map.width));
        playgroundPanel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 5, true));


        for(int i=0; i<Map.width; i++){
            for(int j=0; j<Map.height; j++){
                playground[i][j]=new Position(i, j, map, Color.WHITE, Color.BLACK);
                playgroundPanel.add(playground[i][j]);
//                        playgroundPanel.add(new Position(i, j, map, Color.WHITE, Color.BLACK));
            }
        }


        return playgroundPanel;
    }

    private JPanel makeRightPanel() {
        rightPanel = new JPanel();
        rightPanel.setLayout(new GridLayout(0, 1));
        rightPanel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 5, true));

        textPanel = new JPanel();
        textPanel.setLayout(new GridLayout(0, 1));

        rules = new JTextField(map.getRules().toString(), 22);

        textPanel.add(rules);

        submitButton = new JButton("SUBMIT");
        submitButton.setBackground(Color.CYAN.darker());
        submitButton.setForeground(Color.BLACK);
        submitButton.setBorder(BorderFactory.createLineBorder(
                Color.WHITE, 2, true));
        submitButton.addActionListener(ae ->{
            map.setRules(rules.getText());
            rules.setText(map.getRules());
            System.out.println(map.getRules());
        });

        textPanel.add(submitButton);

        rightPanel.add(textPanel);

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(0, 1));
        buttonPanel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 5, true));

        startStopButton = new JButton("START");
        startStopButton.setBackground(Color.GREEN.darker());
        startStopButton.setForeground(Color.WHITE.brighter());
        startStopButton.addActionListener(ae -> {
            System.out.println("START/STOP JButton Clicked!");
            if (!isTimerRunning)
            {
                startStopButton.setText("STOP");
                timer.start();
                isTimerRunning = true;
                buttonPanel.revalidate();
                buttonPanel.repaint();
            }
            else if (isTimerRunning)
            {
                startStopButton.setText("START");
                timer.stop();
                isTimerRunning = false;
                buttonPanel.revalidate();
                buttonPanel.repaint();
            }
        });
        startStopButton.setBorder(BorderFactory.createLineBorder(
                Color.WHITE, 4, true));
        buttonPanel.add(startStopButton);

        //colourButton = new JButton("BALL COLOUR");
        //colourButton.setBackground(colours[colourCounter]);
        //colourButton.setForeground(Color.WHITE);

        resetButton = new JButton( "RESET" );
        resetButton.setOpaque( true );
        resetButton.setBackground( Color.RED );
        resetButton.setForeground( Color.WHITE );


        resetButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                System.out.println("RESET JButton Clicked!");
                timer.restart();
                //drawingArea.setForegroundForBall(foregroundColour);
                map.clear(playground);
                isTimerRunning=false;
                timer.stop();
                startStopButton.setText("START");
//                timer.start();
                resetButton.revalidate();
                resetButton.repaint();
            }
        });
        resetButton.setBorder(BorderFactory.createLineBorder(
                Color.WHITE, 2, true));
        buttonPanel.add(resetButton);

        exitButton = new JButton("EXIT");
        exitButton.setBackground(Color.RED.darker());
        exitButton.setForeground(Color.WHITE.brighter());
        exitButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                System.out.println("EXIT JButton Clicked!");
                timer.stop();
                System.exit(0);
            }
        });
        exitButton.setBorder(BorderFactory.createLineBorder(
                Color.RED.darker().darker(), 4, true));
        buttonPanel.add(exitButton);

        rightPanel.add(buttonPanel);
        return rightPanel;
    }
}
