/**
 * Programmer: Jacob Scott
 * Program Name: WoolColor
 * Description: enums for working with dye colors
 * Date: Mar 16, 2011
 */
package com.jascotty2;

public class ColorPalette {

    public enum DyeColor {

        BLACK((byte) 0x0),
        RED((byte) 0x1),
        DARK_GREEN((byte) 0x2),
        //GREEN((byte) 0x2),
        BROWN((byte) 0x3),
        BLUE((byte) 0x4),
        PURPLE((byte) 0x5),
        CYAN((byte) 0x6),
        //TEAL((byte) 0x6),
        LIGHT_GRAY((byte) 0x7),
        //SILVER((byte) 0x7),
        DARK_GRAY((byte) 0x8),
        //GRAY((byte) 0x8),
        PINK((byte) 0x9),
        LIGHT_GREEN((byte) 0xA),
        //LIME((byte) 0xA),
        YELLOW((byte) 0xB),
        LIGHT_BLUE((byte) 0xC),
        MAGENTA((byte) 0xD),
        ORANGE((byte) 0xE),
        WHITE((byte) 0xF),
        UNDEF((byte) -1);
        public final byte data;

        DyeColor(byte dat) {
            this.data = dat;
        }

        public static DyeColor valueOf(byte col) {
            for (DyeColor c : DyeColor.values()) {
                if (c.data == col) {
                    return c;
                }
            }
            return DyeColor.UNDEF;
        }

        public static DyeColor valueOf(short col) {
            return valueOf((byte) col);
        }

        public static DyeColor fromWool(byte wool) {
            switch (wool) {
                case 0:
                    return DyeColor.WHITE;
                case 1:
                    return DyeColor.ORANGE;
                case 2:
                    return DyeColor.MAGENTA;
                case 3:
                    return DyeColor.LIGHT_BLUE;
                case 4:
                    return DyeColor.YELLOW;
                case 5:
                    return DyeColor.LIGHT_GREEN;
                case 6:
                    return DyeColor.PINK;
                case 7:
                    return DyeColor.DARK_GRAY;
                case 8:
                    return DyeColor.LIGHT_GRAY;
                case 9:
                    return DyeColor.CYAN;
                case 10:
                    return DyeColor.PURPLE;
                case 11:
                    return DyeColor.BLUE;
                case 12:
                    return DyeColor.BROWN;
                case 13:
                    return DyeColor.DARK_GREEN;
                case 14:
                    return DyeColor.RED;
                case 15:
                    return DyeColor.BLACK;
                default:
                    return DyeColor.UNDEF;
            }
        }

        public short toWool() {
            DyeColor colors[] = new DyeColor[]{
                DyeColor.WHITE,
                DyeColor.ORANGE,
                DyeColor.MAGENTA,
                DyeColor.LIGHT_BLUE,
                DyeColor.YELLOW,
                DyeColor.LIGHT_GREEN,
                DyeColor.PINK,
                DyeColor.DARK_GRAY,
                DyeColor.LIGHT_GRAY,
                DyeColor.CYAN,
                DyeColor.PURPLE,
                DyeColor.BLUE,
                DyeColor.BROWN,
                DyeColor.DARK_GREEN,
                DyeColor.RED,
                DyeColor.BLACK};
            for (short i = 0; i < colors.length; ++i) {
                if (colors[i].data == this.data) {
                    return i;
                }
            }
            return -1;
        }

        public static short toWool(DyeColor dye) {
            return dye.toWool();
        }

        public static short toWool(byte dye) {
            return DyeColor.valueOf(dye).toWool();
        }
    }
    /*
    0    0x0	Ink Sac
    1	 0x1	Rose Red
    2	 0x2	Cactus Green
    3	 0x3	Cocoa Beans
    4	 0x4	Lapis Lazuli
    5	 0x5	Purple Dye
    6	 0x6	Cyan Dye
    7	 0x7	Light Gray Dye
    8	 0x8	Gray Dye
    9	 0x9	Pink Dye
    10	 0xA	Lime Dye
    11	 0xB	Dandelion Yellow
    12	 0xC	Light Blue Dye
    13	 0xD	Magenta Dye
    14	 0xE	Orange Dye
    15	 0xF	Bone Meal
     */

    public static boolean isLightColoredDye(byte dye) {
        return isLightColoredDye(DyeColor.valueOf(dye));
    }

    public static boolean isLightColoredDye(DyeColor col) {
        return col == DyeColor.LIGHT_GRAY || col == DyeColor.PINK
                || col == DyeColor.LIGHT_GREEN || col == DyeColor.YELLOW
                || col == DyeColor.LIGHT_BLUE || col == DyeColor.WHITE;
    }

    public static boolean colorMatch(byte col1, byte col2, byte match1, byte match2) {
        return (col1 == match1 && col2 == match2) || (col2 == match1 && col1 == match2);
    }

    public static boolean colorMatch(byte col1, byte col2, DyeColor match1, DyeColor match2) {
        return (col1 == match1.data && col2 == match2.data) || (col2 == match1.data && col1 == match2.data);
    }

    public static boolean colorMatch(DyeColor col1, DyeColor col2, DyeColor match1, DyeColor match2) {
        return (col1 == match1 && col2 == match2) || (col2 == match1 && col1 == match2);
    }

    public static boolean colorMatch(DyeColor col1, DyeColor col2, DyeColor match1) {
        return col1 == match1 || col2 == match1;
    }

    public static DyeColor MixColors(byte col1, short col2) {
        return MixColors(col1, (byte) col2);
    }

    public static DyeColor MixColors(short col1, short col2) {
        return MixColors((byte) col1, (byte) col2);
    }

    public static DyeColor MixColors(short col1, byte col2) {
        return MixColors((byte) col1, col2);
    }

    /**
     * get what color would result from col2 being applied to col1
     * @param col1 canvas dye color
     * @param col2 dye color
     * @return result color data, or -1 if no change
     */
    public static DyeColor MixColors(byte col1, byte col2) {
        // possible help:
        //http://www.phy.ntnu.edu.tw/ntnujava/index.php?topic=39
        /*
         * order-depenedent:
         * white + color = color
         * black + white = gray
         * brown + white = light gray
         * gray + white = light gray
         * purple + white = magenta ?
         * teal + white = white (? or change to light blue ?)
         * blue + white = light blue
         * green + white = light green
         * red + white = pink
         * light color + white = white
         * enything else given white not specified above becomes white
         * pink + red = red
         * light blue + blue = blue
         * light gray + gray = gray
         * light green + green = green
         * magenta + purple = purple
         * pink + magenta = magenta
         * 
         *
         * any order:
         * color + black = black
         * gray + gray = black
         * light gray + light gray = gray
         * pink + pink = red
         * magenta + magenta = purple
         * light blue + light blue = blue
         * light green + light green = green
         * orange + purple = red
         * cyan + purple = blue
         * yellow + blue = green
         * yellow + red = orange
         * red + white = pink
         * red + blue = purple
         * blue + green = teal
         * purple + pink = magenta
         * yellow + green = lime
         * gray + light color = brown
         * light gray + dark color = brown
         *
         * and every comb not on this list becomes brown:
         * blue + purple
         * blue + magenta
         * purple + red
         * purple + pink
         */
        DyeColor color1 = DyeColor.valueOf(col1);
        DyeColor color2 = DyeColor.valueOf(col2);
        //System.out.println(color1 + " + " + color2);
        // order-depenedent:
        // white + color = color
        if (color1 == DyeColor.WHITE) {
            if (color2 != DyeColor.WHITE) {
                return color2;
            }
        } else if (color2 == DyeColor.WHITE) {
            switch (color1) {
                case BLACK:
                    // black + white = gray
                    return DyeColor.DARK_GRAY;
                case BROWN:
                // brown + white = light gray
                case DARK_GRAY:
                    // gray + white = light gray
                    return DyeColor.LIGHT_GRAY;
                case PURPLE:
                    // purple + white = magenta ?
                    return DyeColor.MAGENTA;
                case CYAN:
                    // teal + white = white ? or light blue ?
                    return DyeColor.WHITE;
                case BLUE:
                    // blue + white = light blue
                    return DyeColor.LIGHT_BLUE;
                case DARK_GREEN:
                    // green + white = light green
                    return DyeColor.LIGHT_GREEN;
                case RED:
                    // red + white = pink
                    return DyeColor.PINK;
            }
            // light color + white = white
            //if (isLightColoredDye(color1))
            // enything given white not specified above becomes white
            return DyeColor.WHITE;
        } else if (color1 == color2) {
            if (color1 == DyeColor.PINK) {
                // pink + pink = red
                return DyeColor.RED;
            } else if (color1 == DyeColor.LIGHT_GRAY) {
                // light gray + light gray = gray
                return DyeColor.DARK_GRAY;
            } else if (color1 == DyeColor.DARK_GRAY) {
                // gray + gray = black
                return DyeColor.BLACK;
            } else if (color1 == DyeColor.MAGENTA) {
                return DyeColor.PURPLE;
            } else if (color1 == DyeColor.LIGHT_BLUE) {
                return DyeColor.BLUE;
            } else if (color1 == DyeColor.LIGHT_GREEN) {
                return DyeColor.DARK_GREEN;
            }
        } else if (color1 == DyeColor.PINK && color2 == DyeColor.RED) {
            return DyeColor.RED;
        } else if (color1 == DyeColor.LIGHT_BLUE && color2 == DyeColor.BLUE) {
            return DyeColor.BLUE;
        } else if (color1 == DyeColor.LIGHT_GRAY && color2 == DyeColor.DARK_GRAY) {
            return DyeColor.DARK_GRAY;
        } else if (color1 == DyeColor.LIGHT_GREEN && color2 == DyeColor.DARK_GREEN) {
            return DyeColor.DARK_GREEN;
        } else if (color1 == DyeColor.MAGENTA && color2 == DyeColor.PURPLE) {
            return DyeColor.PURPLE;
        } else if (color1 == DyeColor.PINK && color2 == DyeColor.MAGENTA) {
            return DyeColor.MAGENTA;
        } else {
            // any order:
            if (colorMatch(color1, color2, DyeColor.BLACK)) {
                if((color1==DyeColor.BLACK && color2!=DyeColor.BLACK)
                        ||(color2==DyeColor.BLACK && color1!=DyeColor.BLACK))
                return DyeColor.BLACK;
            } else if (colorMatch(color1, color2, DyeColor.ORANGE, DyeColor.PURPLE)) {
                // orange + purple = red
                return DyeColor.RED;
            } else if (colorMatch(color1, color2, DyeColor.CYAN, DyeColor.PURPLE)) {
                // cyan + purple = blue
                return DyeColor.BLUE;
            } else if (colorMatch(color1, color2, DyeColor.YELLOW, DyeColor.BLUE)) {
                // yellow + blue = green
                return DyeColor.DARK_GREEN;
            } else if (colorMatch(color1, color2, DyeColor.YELLOW, DyeColor.RED)) {
                // yellow + red = orange
                return DyeColor.ORANGE;
            } else if (colorMatch(color1, color2, DyeColor.RED, DyeColor.WHITE)) {
                // red + white = pink
                return DyeColor.PINK;
            } else if (colorMatch(color1, color2, DyeColor.RED, DyeColor.BLUE)) {
                // red + blue = purple
                return DyeColor.PURPLE;
            } else if (colorMatch(color1, color2, DyeColor.BLUE, DyeColor.DARK_GREEN)) {
                // blue + green = teal
                return DyeColor.CYAN;
            } else if (colorMatch(color1, color2, DyeColor.PURPLE, DyeColor.PINK)) {
                // purple + pink = magenta
                return DyeColor.MAGENTA;
            } else if (colorMatch(color1, color2, DyeColor.YELLOW, DyeColor.LIGHT_GREEN)) {
                // yellow + green = lime
                return DyeColor.LIGHT_GREEN;
            } //*
            else if ((color1 == DyeColor.DARK_GRAY && isLightColoredDye(color2))
                    || (color2 == DyeColor.DARK_GRAY && isLightColoredDye(color1))) {
                // gray + light color = brown
                return DyeColor.BROWN;
            } else if ((color1 == DyeColor.LIGHT_GRAY && !isLightColoredDye(color2))
                    || (color2 == DyeColor.LIGHT_GRAY && !isLightColoredDye(color1))) {
                // light gray + dark color = brown
            }//*/
            // colors to not turn into brown
            else if (!(colorMatch(color1, color2, DyeColor.BLUE, DyeColor.PURPLE)
                    || colorMatch(color1, color2, DyeColor.BLUE, DyeColor.MAGENTA)
                    || colorMatch(color1, color2, DyeColor.PURPLE, DyeColor.RED)
                    || colorMatch(color1, color2, DyeColor.PURPLE, DyeColor.PINK)
                    || colorMatch(color1, color2, DyeColor.DARK_GREEN, DyeColor.LIGHT_GREEN))) {
                return DyeColor.BROWN;
            }
        }
        return DyeColor.UNDEF;
    }
    /*
    public static short MixColors(byte col1, byte col2) {
    // special cases, like white on black returns gray, is order-specific
    // everything else is equal, so don't need to write 1:1 combinations
    if (!(col1 == 0 || col1 == 0 || col1 == 7 || col1 == 8
    || col2 == 0 || col2 == 0 || col2 == 7 || col2 == 8)
    || (!isLightColoredDye(col1) && isLightColoredDye(col2))) {
    // 1 should be >= 2
    if (col1 < col2) {
    byte t = col2;
    col2 = col1;
    col1 = t;
    }
    }
    switch (DyeColor.valueOf(col1)) {
    case BLACK:
    switch (DyeColor.valueOf(col2)) {
    case BLACK:
    return -1;
    case RED:
    case GREEN:
    case BROWN:
    case BLUE:
    case PURPLE:
    case CYAN:
    case SILVER:
    case LIGHT_GRAY:
    case DARK_GRAY:
    case PINK:
    case LIGHT_GREEN:
    case YELLOW:
    case LIGHT_BLUE:
    case MAGENTA:
    case ORANGE:
    case WHITE:
    return DyeColor.BLACK.data;
    }
    break;
    case RED:
    switch (DyeColor.valueOf(col2)) {
    case BLACK:
    return DyeColor.BLACK.data;
    case RED:
    return -1;
    case GREEN:
    return DyeColor.ORANGE.data;
    case BROWN:
    return -1;
    case BLUE:
    return DyeColor.PURPLE.data;
    case PURPLE:
    return DyeColor.MAGENTA.data;
    case CYAN:
    return DyeColor.BLACK.data;
    case LIGHT_GRAY://DyeColor.PINK.data;
    case DARK_GRAY:
    case PINK:
    return -1;
    case LIGHT_GREEN:
    case YELLOW:
    return DyeColor.ORANGE.data;
    case LIGHT_BLUE:
    case MAGENTA:
    case ORANGE:
    case WHITE:
    return DyeColor.BLACK.data;
    
    }
    break;
    case GREEN:
    case BROWN:
    case BLUE:
    case PURPLE:
    case CYAN:
    case LIGHT_GRAY:
    case DARK_GRAY:
    case PINK:
    case LIGHT_GREEN:
    case YELLOW:
    case LIGHT_BLUE:
    case MAGENTA:
    case ORANGE:
    case WHITE:
    byte tval = DyeColor.valueOf(col2).data;
    return tval != DyeColor.UNDEF.data ? tval : -1;
    }
    return -1;
    }
     */
} // end class ColorPalette

