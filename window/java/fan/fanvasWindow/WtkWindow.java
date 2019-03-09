package fan.fanvasWindow;

import java.awt.Dimension;
import java.awt.EventQueue;
//import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.WindowStateListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JFrame;

import fan.fanvasGraphics.Size;
import javax.swing.JPanel;

import fan.fanvasGraphics.Graphics;
import fan.fanvasGraphics.Point;
import fan.fanvasGraphics.Rect;
import fan.fanvasGraphics.Size;

public class WtkWindow implements Window {

  View view;
  AwtCanvas canvas;
  JFrame frame;

  class AwtCanvas extends JPanel {
    private static final long serialVersionUID = 1L;

    @Override
    public void paint(java.awt.Graphics g) {
      Graphics gc = new WtkGraphics((java.awt.Graphics2D)g);
      view.onPaint(gc);
    }
    
    @Override
    public void doLayout() {
      super.doLayout();
      //view.onResize(canvas.getWidth(), canvas.getHeight());
    }
  }

  public WtkWindow(View rootView) {
    canvas = new AwtCanvas();
    frame = new JFrame();
    this.view = rootView;
    ComponentUtils.bindEvent(view, canvas);

    rootView.host(this);
    frame.add(canvas);
  }

  public void show(Size s) {
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //frame.setContentPane(canvas);
    frame.addWindowStateListener(winStateListenner);
    frame.addWindowListener(winListener);
    frame.setLayout(null);

    if (s == null) {
      s = view.getPrefSize(600, 600);
    }
    canvas.setSize((int)s.w, (int)s.h);
    //frame.pack();
    frame.setSize((int)s.w, (int)s.h);

    frame.addComponentListener(new ComponentAdapter(){
      @Override public void componentResized(ComponentEvent e){
          canvas.setSize(frame.getWidth(), frame.getHeight());
      }});

    EventQueue.invokeLater(new Runnable()
    {
      public void run()
      {
        ToolkitEnvPeer.init();
      }
    });

    frame.setVisible(true);

    try {
      Thread.sleep(Long.MAX_VALUE);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public void textInput(TextInput textInput) {
    if (textInput.host() == null) {
      WtkEditText edit = new WtkEditText(textInput);
      textInput.host(edit);
    }
    
    WtkEditText edit = (WtkEditText)textInput.host();
    if (edit.comp().getParent() == null) {
      frame.add(edit.comp(), 0);
    }

    textInput.host().update();
    return;
  }

  @Override
  public void focus() {
    canvas.requestFocus();
  }

  @Override
  public boolean hasFocus() {
    return canvas.hasFocus();
  }

  @Override
  public Point pos() {
    return Point.make(canvas.getX(), canvas.getY());
  }

  @Override
  public Size size() {
    return Size.make(canvas.getWidth(), canvas.getHeight());
  }

  @Override
  public void repaint() {
    canvas.repaint();
  }

  @Override
  public void repaint(Rect r) {
    if (r == null) canvas.repaint();
    else canvas.repaint((int)r.x, (int)r.y, (int)r.w, (int)r.h);
  }

//////////////////////////////////////////////////////////////////////////
// Window Event
//////////////////////////////////////////////////////////////////////////

  private void postDisplayEvent(int id)
  {
    long fid = id;
    switch(id)
    {
    case java.awt.event.WindowEvent.WINDOW_ACTIVATED:
      fid = WindowEvent.activated;
      break;
    case java.awt.event.WindowEvent.WINDOW_OPENED:
      fid = WindowEvent.opened;
      break;
    }

    WindowEvent e = WindowEvent.make(fid);
    view.onWindowEvent(e);
  }

  private WindowListener winListener = new WindowListener()
  {
    public void windowClosing(java.awt.event.WindowEvent e) {
      postDisplayEvent(e.getID());
    }

    public void windowClosed(java.awt.event.WindowEvent e) {
      postDisplayEvent(e.getID());
    }

    public void windowOpened(java.awt.event.WindowEvent e) {
      postDisplayEvent(e.getID());
    }

    public void windowIconified(java.awt.event.WindowEvent e) {
      postDisplayEvent(e.getID());
    }

    public void windowDeiconified(java.awt.event.WindowEvent e) {
      postDisplayEvent(e.getID());
    }

    public void windowActivated(java.awt.event.WindowEvent e) {
      postDisplayEvent(e.getID());
    }

    public void windowDeactivated(java.awt.event.WindowEvent e) {
      postDisplayEvent(e.getID());
    }
  };

  private WindowStateListener winStateListenner = new WindowStateListener()
  {
    public void windowStateChanged(java.awt.event.WindowEvent e)
    {
      postDisplayEvent(e.getID());
    }
  };
}