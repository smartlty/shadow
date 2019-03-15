package com.denachina.shadow.JVMTest;

public class FinalizeEscapeGc {
    public static FinalizeEscapeGc SAVE_HOOK = null;
    public void isAlive(){
        System.out.println("Yes, i am still alive!");
    }
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize method exeuted!");
        FinalizeEscapeGc.SAVE_HOOK = this;
    }

    public static void main(String[] args) throws InterruptedException {
        SAVE_HOOK = new FinalizeEscapeGc();

        // 对象第一次成功拯救自己
        SAVE_HOOK = null;
        System.gc();

        //因为finalize 方法优先级很低 所以暂停0.5s 以等待它
        Thread.sleep(500);
        if (SAVE_HOOK != null){
            SAVE_HOOK.isAlive();
        } else {
            System.out.println("No,i am dead :)");
        }

        // 再次尝试拯救自己但是失败
        SAVE_HOOK = null;
        System.gc();

        //因为finalize 方法优先级很低 所以暂停0.5s 以等待它
        Thread.sleep(500);
        if (SAVE_HOOK != null){
            SAVE_HOOK.isAlive();
        } else {
            System.out.println("No,i am dead :(");
        }
    }
}
