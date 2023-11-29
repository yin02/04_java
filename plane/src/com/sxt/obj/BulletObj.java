package com.sxt.obj;

import com.sxt.GameWin;
import com.sxt.utils.GameUtils;

import java.awt.*;

public class BulletObj extends GameObj {
    public BulletObj(Image img, int x, int y, int width, int height, double speed, GameWin frame) {
        super(img, x, y, width, height, speed, frame);
    }

    @Override
    public void paintSelf(Graphics gImage) {
        super.paintSelf(gImage);
        y += speed;
        //敌方子弹与我方飞机的碰撞检测
        if (this.getRec().intersects(this.frame.planeObj.getRec())){
            GameWin.state = 3;
        }
        //敌方子弹的越界消失 条件 y > 600  改变后的坐标(-300,300)
        if (y > 600){
            this.x = -300;
            this.y = 300;
            GameUtils.removeList.add(this);
        }
    }

    @Override
    public Rectangle getRec() {
        return super.getRec();
    }
}
