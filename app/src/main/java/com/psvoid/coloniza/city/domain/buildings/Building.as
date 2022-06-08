package towns.buildings
{
    import starling.display.Image;

    import towns.people.com.psvoid.coloniza.world.towns.people.Human;

    /**
     * Данные здания
     * @author Psijic_Void
     */
    public class Building
    {
        //**************
        //*** common ***
        //**************

        public function Building(params: Object = null)
        {

            //trace(cost[0][1], cost.length);

            //            this.category = category;
            //            this.id = id;
            //            this.name = name;
            //            this.level = level;
            //            this.buildCost = buildCost;

            //buildingTime = требуемые ресурсы * коэффициент + 25% * уровень
            buildingTime = 10;
            dismantlingTime = buildingTime / 4;
            demolitionTime = dismantlingTime / 2;

            //--- по умолчанию dismantlingCost = 1/4 от cost. Деньги не возвращаются (но так не получится для зданий уровня 2, 3) ---
            //some for

            //--- поиск, во что же обновляется это здание ---
            //some formula

            setAttributes(params);
        }

        public var image: Image;
        public var category: String;
        public var name: String;
        public var level: uint;

        /**
         * список ресурсов, необходимых для постройки этого здания: {id: count})
         */
        public var buildCost: Object;

        /**
         * ресурсы, возвращаемые при разборе здания
         */
        public var dismantlingCost: Object;

        /**
         * Время постройки
         */
        public var buildingTime: uint;
        /**
         * Время разборки (оставшееся)
         */
        public var dismantlingTime: uint;
        /**
         * Время сноса (оставшееся)
         */
        public var demolitionTime: uint;

        /**
         * id здания, которое получится при апгрейде
         */
        public var upgradedTo: uint;

        /**
         * TODO: id необходимых для апгрейда зданий
         */
        public var needBuildingsForUpgrade: Object;

        //******************
        //*** industrial ***
        //******************

        /**
         * Индустрия. Товары на переработку
         */
        public var goodsIn: Object;
        /**
         * Индустрия. Выпускаемая продукция
         */
        public var goodsOut: Object;

        /**
         * Глава/директор
         */
        public var head: com.psvoid.coloniza.world.towns.people.Human;

        /**
         * Максимум управляющих
         */
        public var managersMax: uint;

        /**
         * Управляющие
         */
        public var managers: Vector.<com.psvoid.coloniza.world.towns.people.Human>;

        /**
         * Максимум рабочих
         */
        public var workersMax: uint;

        /**
         * Рабочие
         */
        public var workers: Vector.<com.psvoid.coloniza.world.towns.people.Human>;

        /**
         * Максимум посетителей/жителей
         */
        public var occupantsMax: uint;

        /**
         * Посетители/жители
         */
        public var occupants: Vector.<com.psvoid.coloniza.world.towns.people.Human>;

        /**
         * id вида рабочего
         */
        public var workerType: uint;

        /**
         * Необходимый уровень образования для работы здесь
         */
        public var needEducation: uint = 0;

        /**
         * TODO: specialization
         */
        public var specialization: uint;

        //        public function Building(category:uint, id:uint, name:String, level:uint, cost:Vector.<Number>)

        private var _id: uint;

        public function get id(): uint
        {
            return _id;
        }

        public function set id(value: uint): void
        {
            if (_id == value)
                return;

            _id = value;

            //устанавливаем свойства в соответствии с этим id
            //DataUtil
            setParamsData(id);
        }

        public function setParamsData(id: uint): void
        {
            var item:Object = EmbeddedAssets.industryConfig;
            setAttributes(item[id]);

//            var allItems: Object = EmbeddedAssets.industryConfig;
//            var l: uint = allItems.length;
//            for (var i: int = 0; i < l; i++)
//            {
//                if (allItems[i].id == id)
//                    setAttributes(allItems[i]);
//            }

        }

        /**
         * Установка параметров класса объектом
         */
        public function setAttributes(data: Object): void
        {
            if (!data)
                return;

            for (var param: String in data)
            {
                //установка специальных параметров
                if (param == "image")
                {
                    image = new Image(EmbeddedAssets.assets.getTexture(data.image));
                    continue;
                }

                if (this.hasOwnProperty(param))
                    this[param] = data[param];
            }
        }
    }

}