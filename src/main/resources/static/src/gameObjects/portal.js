class Portal extends Phaser.GameObjects.Sprite{
    constructor(scene,x,y,type){
        super(scene,x,y,type);
        
        scene.add.existing(this);
        scene.physics.world.enable(this);
        this.name = type;
        this.body.setAllowGravity(false);
        this.body.setSize(this.width+15,this.height-40,true);
        
    }
    setAngleBox(angulo){
        if(Math.abs(angulo-this.angle)!=180){
            this.body.setSize(this.body.height,this.body.width,true);

        }
        this.angle=angulo;

    }
}

export default Portal;