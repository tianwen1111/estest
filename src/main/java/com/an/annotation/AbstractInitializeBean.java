package com.an.annotation;

import org.springframework.stereotype.Component;

@Component
public abstract class AbstractInitializeBean  implements Runnable{

    public abstract void initalize(String... args);
    private boolean isForceInit = true;

    @Override
    public void run(){
        if (isForceInit()){
            initalize();
        }
    }

    public boolean isForceInit(){return isForceInit;}

    public void setForceInit(boolean isForceInit){this.isForceInit = isForceInit;}
}
