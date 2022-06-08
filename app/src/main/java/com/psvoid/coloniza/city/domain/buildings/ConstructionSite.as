package towns.buildings
{
    import flash.events.MouseEvent;

    import starling.display.Image;
    import starling.display.Sprite;

    /**
     * ...
     * @author Psijic_Void
     */
    public class ConstructionSite extends Sprite
    {

        public function ConstructionSite()
        {
            var image: Image = new Image(EmbeddedAssets.assets.getTexture("construction"));
//            var image: Image = new Image(EmbeddedAssets.getTexture("icons", "construction"));
            addChild(image);
            //			image.buttonMode = true;
            addEventListener(MouseEvent.CLICK, onClick);
        }

        private function onClick(e: MouseEvent): void
        {
            trace("click");
        }

    }

}