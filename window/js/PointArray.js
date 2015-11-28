//
// Copyright (c) 2011, chunquedong
// Licensed under the Academic Free License version 3.0
//
// History:
//   2011-7-4  Jed Young  Creation
//

fan.fanvasWindow.PointArray = fan.sys.Obj.$extend(fan.sys.Obj);
fan.fanvasWindow.PointArray.prototype.$ctor = function() {}
fan.fanvasWindow.PointArray.prototype.$typeof = function()
{
  return fan.fanvasGraphics.PointArray.$type;
}

fan.fanvasWindow.PointArray.prototype.xArray = null;
fan.fanvasWindow.PointArray.prototype.yArray = null;

fan.fanvasWindow.PointArray.make = function(size)
{
  var p = new fan.fanvasWindow.PointArray();
  p.xArray = new Array(size);
  p.yArray = new Array(size);
  p.m_size = size;
  return p;
}

fan.fanvasWindow.PointArray.prototype.size = function() { return this.m_size; }

fan.fanvasWindow.PointArray.prototype.getX = function(i) { return this.xArray[i]; }

fan.fanvasWindow.PointArray.prototype.getY = function(i) { return this.yArray[i]; }

fan.fanvasWindow.PointArray.prototype.setX = function(i, v) { return this.xArray[i] = v; }

fan.fanvasWindow.PointArray.prototype.setY = function(i, v) { return this.yArray[i] = v; }