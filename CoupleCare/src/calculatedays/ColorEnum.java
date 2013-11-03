package calculatedays;

public enum ColorEnum {
    RED {
        @Override
        public String getColorName() {
            return "red";
        }
        @Override
        public String getHexCode() {
            return "#FF5459";
        }
    },
    BLUE {
        @Override
        public String getColorName() {
            return "blue";
        }
        @Override
        public String getHexCode() {
            return "#0000FF";
        }
    },
    GREEN {
        @Override
        public String getColorName() {
            return "green";
        }
        @Override
        public String getHexCode() {
            return "#00FF00";
        }
    };
    // rojo: #FF0000
    // azul: #0000FF
    // verde: #00FF00
  public abstract String getColorName();
  public  abstract String getHexCode();
}
