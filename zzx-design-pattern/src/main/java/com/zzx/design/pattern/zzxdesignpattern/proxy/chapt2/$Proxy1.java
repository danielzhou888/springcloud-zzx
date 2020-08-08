package com.zzx.design.pattern.zzxdesignpattern.proxy.chapt2;
import java.lang.reflect.Method;
public class $Proxy1 implements com.zzx.design.pattern.zzxdesignpattern.proxy.chapt2.Moveable{
   com.zzx.design.pattern.zzxdesignpattern.proxy.chapt2.InvocationHandler h;
   public $Proxy1(com.zzx.design.pattern.zzxdesignpattern.proxy.chapt2.InvocationHandler h) {
       this.h = h;
   }
@Override
public void move() {
   try {
       Method md = com.zzx.design.pattern.zzxdesignpattern.proxy.chapt2.Moveable.class.getMethod("move");
       h.invoke(this, md);
   } catch(Exception e) {
       e.printStackTrace();
   }
}
}