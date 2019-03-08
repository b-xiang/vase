//
// Copyright (c) 2011, chunquedong
// Licensed under the Academic Free License version 3.0
//
// History:
//   2011-7-4  Jed Young  Creation
//

using fanvasGraphics
using fanvasWindow
using fanvasMath

**
** Image Test
**
@Js
class ImageTest
{
  Void main()
  {
    view := MyImageView()
    win := Toolkit.cur.build()
    win.add(view)

    win.show()
  }
}

@Js
class MyImageView : View
{
  Int i := 0

  override NativeView? host

  Image p := BufImage.fromUri(`fan://icons/x16/folder.png`) |p|
  {
    //image filter
    for (i:=0; i < p.size.w; ++i)
    {
      for (j:=0; j < p.size.h; ++j)
      {
        c := p.getPixel(i,j)
        //echo(c)
        c = c.and(0xffff0000)
        //echo(c)
        p.setPixel(i, j, c)
      }
    }
  }

  new make()
  {
  }

  override Void onPaint(Graphics g) {
    g.drawImage(p, 0, 0)
  }
}

