package calculateDays;


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
			return "BLUE";
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
	},
	MAGENTA{
		@Override
		public String getColorName() {
			return "maroon";
		}

		@Override
		public String getHexCode() {
			return "#7F0003";
		}
	},
	PINK {
		@Override
		public String getColorName() {
			return "pink";
		}

		@Override
		public String getHexCode() {
			return "#FF5459";
		}
	},
	DARKGREEN {
		@Override
		public String getColorName() {
			return "darkgreen";
		}

		@Override
		public String getHexCode() {
			return "#00550C";
		}
		
	},
	LIGHTGREEN {
		@Override
		public String getColorName() {
			return "lightgreen";
		}

		@Override
		public String getHexCode() {
			return "#6FDC45";
		}
	},
	BROWN {
		@Override
		public String getColorName() {
			return "brown";
		}

		@Override
		public String getHexCode() {
			return "#AA8E00";
		}
	},
	YELLOW{
		@Override
		public String getColorName() {
			return "YELLOW";
		}

		@Override
		public String getHexCode() {
			return "#FFFF66";
		}
	}, 
	ORANGE{
		@Override
		public String getColorName() {
			return "orange";
		}

		@Override
		public String getHexCode() {
			return "#FF500";
		}
	},
	CYAN{
		@Override
		public String getColorName() {
			return "CYAN";
		}

		@Override
		public String getHexCode() {
			return "#FF500";
		}
	};
	// rojo: #FF0000
	// azul: #0000FF
	// verde: #00FF00
	public abstract String getColorName();

	public abstract String getHexCode();
}
