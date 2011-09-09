//
// Copyright (c) 2011, chunquedong
// Licensed under the Academic Free License version 3.0
//
// History:
//   2011-08-14  Jed Young  Creation
//

package fan.gfx2Imp;

import fan.sys.*;
import fan.fwt.*;
import fan.gfx.*;
import fan.gfx2.*;

import java.awt.Frame;
import java.awt.Graphics2D;

import org.eclipse.swt.*;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.events.*;
import org.eclipse.swt.awt.SWT_AWT;

public class Canvas2Peer extends CanvasPeer implements PaintListener
{
  public static Canvas2Peer make(Canvas2 self)
    throws Exception
  {
    Canvas2Peer peer = new Canvas2Peer();
    ((fan.fwt.Widget)self).peer = peer;
    peer.self = self;
    return peer;
  }

  public Widget create(Widget parent)
  {
    int style = SWT.NO_BACKGROUND | SWT.EMBEDDED;
    if (((Canvas2)self).buffered()) style |= SWT.DOUBLE_BUFFERED;
    Canvas c = new Canvas((Composite)parent, style);
    c.addPaintListener(this);
    return c;
  }

  public void paintControl(PaintEvent e)
  {
    Graphics2 g;

    String name = Gfx2.engineName();
    if (name.equals("AWT"))
    {
      Frame f = SWT_AWT.new_Frame((Composite)this.control());
      Graphics2D gc = (Graphics2D)f.getGraphics();
      g = new AwtGraphics(gc);
    }
    else if(name.equals("SWT"))
    {
      g = new FwtGraphics2(e);
    }
    else
    {
      g = new FwtGraphics2(e);
    }

    try
    {
      ((Canvas2)self).onPaint(g);
      ((Canvas2)self).onPaint2(g);
    }
    finally
    {
      g.dispose();
    }
  }

  public void setCaret(Canvas2 self, long x, long y, long w, long h)
  {
    Caret caret = new Caret((Canvas)this.control(), SWT.NONE);
    caret.setBounds((int)x, (int)y, (int)w, (int)h);
  }
}