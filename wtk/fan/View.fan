//
// Copyright (c) 2011, chunquedong
// Licensed under the Academic Free License version 3.0
//
// History:
//   2012-7-7  Jed Young  Creation
//

using fgfxGraphics
using concurrent

**
** root view
**
@Js
mixin View
{
  **
  ** do paint at here
  **
  virtual Void onPaint(Graphics g) {}

  virtual Void onMotionEvent(MotionEvent e) {}

  virtual Void onKeyEvent(KeyEvent e) {}

  virtual Void onDisplayEvent(DisplayEvent e) {}

  **
  ** get native host
  **
  abstract NativeView? nativeView

  **
  ** get prefer size
  **
  virtual Size getPrefSize(Int hintsWidth, Int hintsHeight)  { Size(400, 400) }
}

**
** native host view
**
@Js
mixin NativeView
{
  **
  ** request repaint
  **
  abstract Void repaint(Rect? dirty := null)

  **
  ** get current size
  **
  abstract Size size()
  abstract Point pos()

  **
  ** return true if has focus
  **
  abstract Bool hasFocus()

  **
  ** request focus
  **
  abstract Void focus()

  **
  ** get the window
  **
  abstract Window win()
}

