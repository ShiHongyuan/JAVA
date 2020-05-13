package designPattern.facade;

import designPattern.facade.pagemaker.PageMaker;

/**
 * 简单窗口模式：把复杂的逻辑处理流程的类整合起来，引入Facade角色，就是封装在一个对外的API里，外部不需要关系内部的逻辑，只需要调用对外API就行
 *              处理的对外API变少了，意味着程序与外部的关联关系弱化了，使内部的一整个包集合可以被作为组件复用，内部的修改也不会影响外部的使用
 *              关键就是需要决定对外开放哪些API，开放多了，内部的修改牵连的外部修改就会复杂，开放适量够用的API即可。
 */
public class Main {
    public static void main (String[] args) {
        PageMaker.makeWelcomePage("shihongyuan@163.com", "welcome.html");
        PageMaker.makeLinkPage("linkpage.html");
    }
}
