package Setup;

import com.badlogic.gdx.tools.imagepacker.TexturePacker2;

public class TextureSetup {
        public static void main (String[] args) throws Exception {
                TexturePacker2.process("D:\\Dropbox\\workspace\\AndroidGame-android\\assets\\images", "D:\\Dropbox\\workspace\\AndroidGame-android\\assets\\images\\textures", "textures.pack");
        }
}