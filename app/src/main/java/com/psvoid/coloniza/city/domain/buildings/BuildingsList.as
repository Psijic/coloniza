package towns.buildings
{
    import constants.BuildingType;

    /**
     * List of all buildings, singleton
     * @author Psijic_Void
     */
    public class BuildingsList
    {


        public static var refinery: Building = new Building(BuildingType.INDUSTRY);

        public function BuildingsList()
        {
            throw new Error("Class is singleton.");
        }

        public static function create(): void
        {

        }

    }

}