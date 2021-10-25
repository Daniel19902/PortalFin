import Bootloader from "./bootloader.js";
import Scene_play from "./scene_play.js";

const confing = {
    width: 960,
    height: 608,
    parent: "container",
    type: Phaser.AUTO,
    physics: {
        default: "arcade",
        arcade:{
            gravity: {
                y: 500
            },
            debug: true
        }

    },
    scene: [
        Bootloader,
        Scene_play
    ]
}

new Phaser.Game(confing);

