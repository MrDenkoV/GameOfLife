//package gol.vis;
//
//import java.awt.*;
//
//import java.awt.event.*;
//
//import javax.swing.*;
//
//// https://stackoverflow.com/questions/9849950/something-seems-wrong-with-the-layout-jbutton-showing-unexpected-behaviour-at-r/9852739#9852739
//
//public class T1{
//    private int x;
//    private int y;
//    private boolean positiveX;
//    private boolean positiveY;
//    private boolean isTimerRunning;
//    private int speedValue;
//    private int diameter;
//    private DrawingArea drawingArea;
//    private Timer timer;
//    private int colourCounter;
//    Color[] colours = {
//            Color.BLUE.darker(),
//            Color.MAGENTA.darker(),
//            Color.BLACK.darker(),
//            Color.RED.darker(),
//            Color.PINK.darker(),
//            Color.CYAN.darker(),
//            Color.DARK_GRAY.darker(),
//            Color.YELLOW.darker(),
//            Color.GREEN.darker()
//    };
//
//    private Color backgroundColour;
//    private Color foregroundColour;
//
//    private ActionListener timerAction = new ActionListener()
//    {
//        public void actionPerformed(ActionEvent ae)
//        {
//            x = getX();
//            y = getY();
//            drawingArea.setXYColourValues(x, y, backgroundColour
//                    , foregroundColour);
//        }
//    };
//
//    private JPanel buttonPanel;
//    private JButton startStopButton;
//    private JButton speedIncButton;
//    private JButton speedDecButton;
//    private JButton resetButton;
//    private JButton colourButton;
//    private JButton exitButton;
//
//    private ComponentAdapter componentAdapter = new ComponentAdapter()
//    {
//        public void componentResized(ComponentEvent ce)
//        {
//            timer.restart();
//            startStopButton.setText("STOP");
//            isTimerRunning = true;
//        }
//    };
//
//    public T1()
//    {
//        x = y = 0;
//        positiveX = positiveY = true;
//        speedValue = 1;
//        colourCounter = 0;
//        isTimerRunning = false;
//        diameter = 50;
//        backgroundColour = Color.WHITE.brighter();
//        foregroundColour = colours[colourCounter];
//        timer = new Timer(10, timerAction);
//    }
//
//    private void createAndDisplayGUI()
//    {
//        JFrame frame = new JFrame("Ball Animation");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setLocationByPlatform(true);
//
//        drawingArea = new DrawingArea(x, y
//                , backgroundColour, foregroundColour, diameter);
//        drawingArea.addComponentListener(componentAdapter);
//
//        frame.add(makeButtonPanel(), BorderLayout.LINE_END);
//        frame.add(drawingArea, BorderLayout.CENTER);
//        frame.pack();
//        frame.setVisible(true);
//    }
//
//    private JPanel makeButtonPanel()
//    {
//        buttonPanel = new JPanel();
//        buttonPanel.setLayout(new GridLayout(0, 1));
//        buttonPanel.setBorder(BorderFactory.createLineBorder(
//                Color.DARK_GRAY, 5, true));
//
//        startStopButton = new JButton("START");
//        startStopButton.setBackground(Color.GREEN.darker());
//        startStopButton.setForeground(Color.WHITE.brighter());
//        startStopButton.addActionListener(new ActionListener()
//        {
//            public void actionPerformed(ActionEvent ae)
//            {
//                System.out.println("START/STOP JButton Clicked!");
//                if (!isTimerRunning)
//                {
//                    startStopButton.setText("STOP");
//                    timer.start();
//                    isTimerRunning = true;
//                    buttonPanel.revalidate();
//                    buttonPanel.repaint();
//                }
//                else if (isTimerRunning)
//                {
//                    startStopButton.setText("START");
//                    timer.stop();
//                    isTimerRunning = false;
//                    buttonPanel.revalidate();
//                    buttonPanel.repaint();
//                }
//            }
//        });
//        startStopButton.setBorder(BorderFactory.createLineBorder(
//                Color.WHITE, 4, true));
//        buttonPanel.add(startStopButton);
//
//        //colourButton = new JButton("BALL COLOUR");
//        //colourButton.setBackground(colours[colourCounter]);
//        //colourButton.setForeground(Color.WHITE);
//
//        colourButton = new JButton( "BALL COLOUR" );
//        colourButton.setOpaque( true );
//        colourButton.setBackground( colours[ colourCounter ] );
//        colourButton.setForeground( Color.WHITE );
//
//
//        colourButton.addActionListener(new ActionListener()
//        {
//            public void actionPerformed(ActionEvent ae)
//            {
//                System.out.println("COLOUR JButton Clicked!");
//                //timer.restart();
//                colourCounter++;
//                if (colourCounter == 9)
//                    colourCounter = 0;
//                foregroundColour = colours[colourCounter];
//                drawingArea.setXYColourValues(x, y, backgroundColour
//                        , foregroundColour);
//                //drawingArea.setForegroundForBall(foregroundColour);
//                colourButton.setBackground(foregroundColour);
//                colourButton.revalidate();
//                colourButton.repaint();
//                //timer.start();
//            }
//        });
//        colourButton.setBorder(BorderFactory.createLineBorder(
//                Color.WHITE, 2, true));
//        buttonPanel.add(colourButton);
//
//        exitButton = new JButton("EXIT");
//        exitButton.setBackground(Color.RED.darker());
//        exitButton.setForeground(Color.WHITE.brighter());
//        exitButton.addActionListener(new ActionListener()
//        {
//            public void actionPerformed(ActionEvent ae)
//            {
//                System.out.println("EXIT JButton Clicked!");
//                timer.stop();
//                System.exit(0);
//            }
//        });
//        exitButton.setBorder(BorderFactory.createLineBorder(
//                Color.RED.darker().darker(), 4, true));
//        buttonPanel.add(exitButton);
//
//        return buttonPanel;
//    }
//
//    private int getX()
//    {
//        if (x < 0)
//            positiveX = true;
//        else if (x >= drawingArea.getWidth() - diameter)
//            positiveX = false;
//        return (calculateX());
//    }
//
//    private int calculateX()
//    {
//        if (positiveX)
//            return (x += speedValue);
//        else
//            return (x -= speedValue);
//    }
//
//    private int getY()
//    {
//        if (y < 0)
//            positiveY = true;
//        else if (y >= drawingArea.getHeight() - diameter)
//            positiveY = false;
//        return (calculateY());
//    }
//
//    private int calculateY()
//    {
//        if (positiveY)
//            return (y += speedValue);
//        else
//            return (y -= speedValue);
//    }
//
//    public static void run()
//    {
//        Runnable runnable = new Runnable()
//        {
//            public void run()
//            {
//                new T1().createAndDisplayGUI();
//            }
//        };
//        SwingUtilities.invokeLater(runnable);
//    }
//}