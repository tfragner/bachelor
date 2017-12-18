package at.meroff.bac.models;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class Main3 {

    public static Set<Field2> fields = new HashSet<>();

    public static void main(String[] args) {

        createFields();

        fields.stream()
                .sorted(Comparator.comparing(Field2::getName))
                .forEach(field -> {
                    System.out.println("#####  " + field.getName() + "  #####");
                    //System.out.println(field.getVectorSummary());
                    //System.out.println(field.getSubjectsBoundaries());
                    //System.out.println(field.getTasksBoundaries());
                    //System.out.println(field.isSubjectsIntersectingTasks());
                    //System.out.println(field.getSubjectsIntersectionTasks());
                    //field.findSmallestDistancesBetweenSubjectsAndTasks();
                    field.findRelations2();
                });


    }

    private static void createFields() {
        fields.add(getField01());
        fields.add(getField02());
        fields.add(getField03());
        fields.add(getField04());
        fields.add(getField05());
        fields.add(getField06());
        fields.add(getField07());
        fields.add(getField08());
        fields.add(getField09());
        fields.add(getField10());
        fields.add(getField11());
        fields.add(getField12());
        fields.add(getField13());
        fields.add(getField14());
        fields.add(getField15());
        fields.add(getField16());
        fields.add(getField17());
    }

    public static Field2 getField01() {
        Field2 field = new Field2();

        field.setName("Beispiel 01");

        field.addCard(new Subject(50, 719, 580, 730, 387, 1081, 408, 1070, 600));
        field.addCard(new Subject(44, 1575, 583, 1575, 399, 1898, 399, 1898, 583));
        field.addCard(new Subject(41, 2419, 565, 2394, 365, 2729, 324, 2753, 525));

        field.addCard(new Task(18, 647, 942, 667, 734, 1044, 771, 1024, 979));
        field.addCard(new Task(9, 605, 1266, 627, 1044, 1038, 1085, 1016, 1306));
        field.addCard(new Task(33, 387, 2165, 412, 1890, 865, 1930, 841, 2205));
        field.addCard(new Task(15, 1569, 1497, 1569, 1267, 1928, 1267, 1928, 1497));
        field.addCard(new Task(51, 1553, 1971, 1553, 1738, 1932, 1737, 1933, 1971));
        field.addCard(new Task(3, 2625, 1842, 2598, 1585, 3023, 1541, 3050, 1797));

        field.addCard(new Transfer(28, 1111, 1345, 1111, 1122, 1487, 1122, 1487, 1345));
        field.addCard(new Transfer(1, 989, 2077, 989, 1812, 1394, 1812, 1394, 2077));
        field.addCard(new Transfer(22, 2091, 1609, 2078, 1378, 2474, 1356, 2487, 1587));
        field.addCard(new Transfer(16, 2102, 1998, 2084, 1735, 2507, 1706, 2525, 1968));

        return field;
    }

    public static Field2 getField02() {
        Field2 field = new Field2();

        field.setName("Beispiel 02");

        field.addCard(new Subject(50, 1067, 275, 1045, 459, 674, 416, 696, 231));
        field.addCard(new Subject(44, 1905, 272, 1907, 449, 1567, 453, 1565, 276));
        field.addCard(new Subject(41, 2728, 233, 2769, 427, 2407, 504, 2366, 310));

        field.addCard(new Task(18, 764, 740, 795, 539, 1192, 599, 1161, 800));
        field.addCard(new Task(9, 667, 1271, 478, 1047, 866, 719, 1055, 943));
        field.addCard(new Task(33, 277, 2284, 162, 1947, 682, 1769, 798, 2106));
        field.addCard(new Task(15, 1510, 1145, 1749, 994, 2006, 1403, 1767, 1553));
        field.addCard(new Task(51, 1816, 2083, 1531, 1942, 1754, 1492, 2039, 1633));
        field.addCard(new Task(3, 2684, 1727, 2746, 1441, 3234, 1545, 3173, 1832));

        field.addCard(new Transfer(28, 1020, 1230, 1044, 997, 1463, 1040, 1439, 1273));
        field.addCard(new Transfer(1, 829, 1802, 1074, 1553, 1443, 1916, 1198, 2165));
        field.addCard(new Transfer(22, 2112, 1516, 2094, 1271, 2533, 1239, 2552, 1484));
        field.addCard(new Transfer(16, 2128, 1943, 2102, 1654, 2583, 1611, 2609, 1899));

        return field;
    }

    public static Field2 getField03() {
        Field2 field = new Field2();

        field.setName("Beispiel 03");

        field.addCard(new Subject(50, 288, 539, 301, 424, 519, 451, 505, 566));
        field.addCard(new Subject(44, 1119, 610, 1127, 734, 920, 747, 912, 623));
        field.addCard(new Subject(41, 1361, 421, 1342, 310, 1544, 276, 1563, 386));

        field.addCard(new Task(18, 212, 766, 225, 635, 462, 659, 449, 790));
        field.addCard(new Task(9, 158, 1034, 175, 873, 440, 901, 423, 1062));
        field.addCard(new Task(33, 909, 958, 896, 806, 1142, 785, 1155, 938));
        field.addCard(new Task(15, 919, 1188, 903, 1024, 1152, 999, 1168, 1163));
        field.addCard(new Task(3, 1343, 273, 1334, 171, 1541, 152, 1550, 254));
        field.addCard(new Task(51, 1341, 139, 1331, 52, 1515, 30, 1525, 118));

        field.addCard(new Transfer(1, 599, 639, 606, 515, 822, 528, 815, 652));
        field.addCard(new Transfer(28, 1229, 768, 1211, 636, 1442, 604, 1460, 736));
        field.addCard(new Transfer(16, 1491, 786, 1515, 942, 1249, 984, 1224, 828));
        field.addCard(new Transfer(22, 1237, 371, 1234, 487, 1026, 481, 1029, 365));

        return field;
    }

    public static Field2 getField04() {
        Field2 field = new Field2();

        field.setName("Beispiel 04");

        field.addCard(new Subject(38, 511, 468, 406, 394, 535, 211, 640, 284));
        field.addCard(new Subject(17, 783, 280, 800, 178, 994, 210, 977, 313));
        field.addCard(new Subject(41, 1098, 404, 1220, 436, 1163, 653, 1041, 621));
        field.addCard(new Subject(44, 791, 858, 778, 732, 988, 711, 1000, 837));
        field.addCard(new Subject(50, 334, 683, 435, 570, 617, 733, 516, 845));

        field.addCard(new Task(60, 403, 132, 499, 211, 356, 387, 259, 308));
        field.addCard(new Task(45, 276, 57, 373, 129, 243, 305, 146, 233));
        field.addCard(new Task(9, 174, 820, 293, 701, 498, 905, 378, 1024));
        field.addCard(new Task(39, 27, 944, 154, 831, 349, 1048, 223, 1162));
        field.addCard(new Task(18, 783, 1066, 783, 918, 1014, 918, 1014, 1066));
        field.addCard(new Task(3, 1395, 696, 1258, 674, 1295, 449, 1432, 471));
        field.addCard(new Task(51, 1582, 702, 1456, 707, 1446, 483, 1573, 478));
        field.addCard(new Task(15, 1024, 66, 1003, 164, 817, 125, 837, 27));

        field.addCard(new Transfer(28, 164, 445, 284, 455, 266, 681, 145, 671));
        field.addCard(new Transfer(1, 789, 571, 677, 495, 818, 289, 930, 365));
        field.addCard(new Transfer(22, 1230, 672, 1331, 779, 1133, 966, 1032, 859));
        field.addCard(new Transfer(16, 1134, 199, 1230, 109, 1397, 287, 1301, 377));

        return field;
    }

    public static Field2 getField05() {
        Field2 field = new Field2();

        field.setName("Beispiel 05");

        field.addCard(new Subject(50, 245, 756, 268, 616, 514, 655, 492, 796));
        field.addCard(new Subject(17, 1322, 615, 1345, 752, 1101, 793, 1078, 656));
        field.addCard(new Subject(38, 1256, 198, 1265, 315, 1041, 331, 1032, 214));

        field.addCard(new Task(45, 587, 366, 572, 490, 341, 463, 355, 339));
        field.addCard(new Task(42, 658, 735, 672, 594, 909, 617, 896, 758));
        field.addCard(new Task(33, 423, 1022, 435, 858, 701, 878, 689, 1042));
        field.addCard(new Task(15, 58, 1001, 88, 842, 350, 892, 320, 1051));
        field.addCard(new Task(60, 54, 587, 74, 454, 316, 491, 295, 625));
        field.addCard(new Task(3, 1024, 166, 1018, 286, 802, 275, 808, 156));
        field.addCard(new Task(51, 1284, 287, 1271, 180, 1488, 153, 1501, 259));
        field.addCard(new Task(18, 1053, 514, 1037, 389, 1260, 360, 1276, 485));
        field.addCard(new Task(9, 1113, 828, 1119, 984, 859, 995, 852, 839));
        field.addCard(new Task(39, 1530, 834, 1560, 997, 1292, 1046, 1262, 884));

        return field;
    }

    public static Field2 getField06() {
        Field2 field = new Field2();

        field.setName("Beispiel 06");

        field.addCard(new Subject(50, 231, 265, 246, 150, 461, 178, 446, 293));
        field.addCard(new Subject(17, 165, 635, 182, 503, 421, 533, 404, 666));
        field.addCard(new Subject(38, 123, 1077, 137, 912, 406, 935, 392, 1099));

        field.addCard(new Task(9, 546, 277, 546, 160, 765, 160, 765, 277));
        field.addCard(new Task(42, 818, 250, 817, 136, 1022, 134, 1023, 248));
        field.addCard(new Task(18, 1132, 254, 1118, 142, 1333, 115, 1347, 226));
        field.addCard(new Task(45, 507, 643, 519, 510, 757, 532, 744, 665));
        field.addCard(new Task(3, 817, 650, 816, 507, 1054, 505, 1056, 647));
        field.addCard(new Task(39, 1159, 651, 1155, 512, 1399, 506, 1403, 644));
        field.addCard(new Task(15, 478, 1084, 490, 918, 745, 935, 733, 1101));
        field.addCard(new Task(33, 851, 1095, 848, 925, 1114, 919, 1118, 1090));
        field.addCard(new Task(60, 1231, 1104, 1211, 935, 1476, 904, 1496, 1073));

        field.addCard(new Transfer(16, 611, 448, 611, 318, 842, 318, 842, 448));
        field.addCard(new Transfer(1, 1053, 434, 1041, 307, 1267, 285, 1279, 413));
        field.addCard(new Transfer(28, 984, 861, 980, 714, 1224, 706, 1229, 854));

        return field;
    }

    public static Field2 getField07() {
        Field2 field = new Field2();

        field.setName("Beispiel 07");

        field.addCard(new Subject(50, 130, 264, 146, 153, 360, 183, 344, 294));
        field.addCard(new Subject(17, 66, 632, 82, 503, 316, 533, 300, 662));
        field.addCard(new Subject(38, -10, 1054, 9, 899, 268, 930, 250, 1085));
        field.addCard(new Subject(14, 1259, 264, 1247, 154, 1470, 129, 1483, 239));
        field.addCard(new Subject(41, 1332, 662, 1319, 528, 1523, 508, 1536, 642));
        field.addCard(new Subject(44, 1369, 1078, 1350, 936, 1589, 903, 1608, 1046));

        field.addCard(new Task(9, 443, 281, 443, 167, 659, 167, 659, 281));
        field.addCard(new Task(42, 743, 263, 743, 152, 942, 150, 943, 261));
        field.addCard(new Task(18, 1009, 267, 997, 158, 1198, 137, 1210, 245));
        field.addCard(new Task(45, 402, 642, 414, 513, 646, 534, 634, 664));
        field.addCard(new Task(3, 665, 648, 665, 512, 890, 512, 890, 648));
        field.addCard(new Task(39, 1025, 652, 1023, 519, 1248, 515, 1250, 649));
        field.addCard(new Task(15, 371, 1062, 383, 908, 630, 929, 617, 1083));
        field.addCard(new Task(33, 728, 1072, 728, 911, 975, 911, 975, 1072));
        field.addCard(new Task(60, 1080, 1074, 1068, 916, 1306, 899, 1318, 1057));

        field.addCard(new Transfer(16, 506, 453, 506, 325, 731, 325, 731, 453));
        field.addCard(new Transfer(1, 930, 441, 922, 319, 1132, 305, 1140, 427));
        field.addCard(new Transfer(28, 857, 850, 856, 711, 1083, 708, 1085, 847));

        return field;
    }

    public static Field2 getField08() {
        Field2 field = new Field2();

        field.setName("Beispiel 08");

        field.addCard(new Subject(50, 809, 793, 809, 572, 1213, 572, 1213, 793));
        field.addCard(new Subject(44, 301, 1794, 526, 1530, 947, 1889, 721, 2153));
        field.addCard(new Subject(17, 1463, 1147, 1463, 912, 1887, 912, 1887, 1147));
        field.addCard(new Subject(38, 1971, 664, 1971, 450, 2381, 450, 2381, 664));
        field.addCard(new Subject(41, 2506, 1639, 2453, 1356, 2919, 1268, 2972, 1551));

        field.addCard(new Task(3, 727, 1122, 727, 866, 1170, 866, 1170, 1122));
        field.addCard(new Task(18, 603, 1428, 603, 1165, 1061, 1165, 1061, 1428));
        field.addCard(new Task(51, 14, 2121, 255, 1831, 685, 2188, 444, 2478));
        field.addCard(new Task(42, 1464, 1467, 1453, 1206, 1897, 1189, 1907, 1450));
        field.addCard(new Task(60, 1452, 1816, 1446, 1520, 1907, 1510, 1913, 1807));
        field.addCard(new Task(39, 1430, 2192, 1430, 1869, 1941, 1869, 1941, 2192));
        field.addCard(new Task(15, 1943, 930, 1964, 690, 2378, 726, 2357, 966));
        field.addCard(new Task(33, 1937, 1237, 1937, 982, 2389, 982, 2389, 1237));
        field.addCard(new Task(45, 1956, 1572, 1956, 1300, 2417, 1300, 2417, 1572));
        field.addCard(new Task(9, 1953, 1943, 1953, 1634, 2456, 1634, 2456, 1943));
        field.addCard(new Task(30, 2530, 2002, 2492, 1692, 3008, 1629, 3046, 1940));

        return field;
    }

    public static Field2 getField09() {
        Field2 field = new Field2();

        field.setName("Beispiel 09");

        field.addCard(new Subject(50, 488, 398, 488, 275, 687, 275, 687, 398));
        field.addCard(new Subject(44, 286, 911, 386, 782, 583, 935, 483, 1064));
        field.addCard(new Subject(17, 821, 603, 820, 477, 1028, 476, 1029, 602));
        field.addCard(new Subject(38, 1077, 345, 1077, 222, 1287, 222, 1287, 345));
        field.addCard(new Subject(41, 1320, 846, 1303, 704, 1521, 678, 1538, 819));

        field.addCard(new Task(3, 459, 576, 456, 441, 672, 437, 675, 572));
        field.addCard(new Task(18, 412, 731, 406, 600, 622, 590, 628, 722));
        field.addCard(new Task(51, 168, 1057, 272, 922, 472, 1077, 368, 1211));
        field.addCard(new Task(42, 821, 751, 820, 621, 1030, 619, 1032, 749));
        field.addCard(new Task(60, 820, 924, 815, 782, 1031, 775, 1036, 917));
        field.addCard(new Task(39, 812, 1103, 811, 955, 1044, 953, 1045, 1100));
        field.addCard(new Task(15, 1062, 487, 1076, 355, 1285, 376, 1272, 509));
        field.addCard(new Task(33, 1054, 647, 1054, 512, 1276, 512, 1276, 647));
        field.addCard(new Task(45, 1057, 813, 1057, 676, 1278, 676, 1278, 813));
        field.addCard(new Task(9, 1050, 985, 1059, 839, 1297, 854, 1288, 999));
        field.addCard(new Task(30, 1318, 1016, 1313, 869, 1543, 861, 1548, 1008));

        field.addCard(new Transfer(22, 1325, 519, 1325, 391, 1546, 391, 1546, 519));
        field.addCard(new Transfer(1, 199, 247, 214, 127, 417, 152, 402, 273));
        field.addCard(new Transfer(28, 90, 766, 90, 633, 312, 633, 312, 766));
        field.addCard(new Transfer(16, 777, 394, 776, 266, 989, 264, 990, 392));

        return field;
    }

    public static Field2 getField10() {
        Field2 field = new Field2();

        field.setName("Beispiel 10");

        field.addCard(new Subject(8, 150, 361, 55, 268, 242, 74, 338, 167));
        field.addCard(new Subject(50, 318, 1047, 227, 1213, -29, 1072, 62, 906));
        field.addCard(new Subject(17, 949, 958, 973, 1114, 726, 1153, 702, 997));
        field.addCard(new Subject(38, 1497, 819, 1633, 932, 1458, 1141, 1322, 1028));
        field.addCard(new Subject(44, 1216, 159, 1260, 44, 1462, 122, 1417, 237));
        field.addCard(new Subject(41, 649, 193, 645, 78, 834, 71, 837, 187));

        field.addCard(new Task(3, 287, 470, 166, 374, 327, 171, 448, 267));
        field.addCard(new Task(27, 399, 559, 287, 466, 448, 272, 560, 365));
        field.addCard(new Task(51, 413, 866, 330, 1016, 89, 883, 173, 732));
        field.addCard(new Task(39, 514, 716, 434, 855, 178, 709, 258, 570));
        field.addCard(new Task(30, 936, 781, 954, 932, 714, 961, 696, 809));
        field.addCard(new Task(60, 910, 604, 926, 747, 697, 773, 681, 630));
        field.addCard(new Task(42, 1368, 681, 1487, 798, 1290, 998, 1171, 881));
        field.addCard(new Task(9, 1221, 539, 1347, 661, 1158, 857, 1032, 735));
        field.addCard(new Task(45, 1151, 286, 1199, 167, 1420, 257, 1372, 375));
        field.addCard(new Task(33, 1100, 418, 1151, 291, 1378, 383, 1326, 509));
        field.addCard(new Task(15, 650, 323, 649, 208, 848, 205, 850, 321));
        field.addCard(new Task(18, 640, 471, 640, 350, 856, 349, 856, 470));

        return field;
    }

    public static Field2 getField11() {
        Field2 field = new Field2();

        field.setName("Beispiel 11");

        field.addCard(new Subject(8, 259, 324, 167, 235, 337, 57, 430, 146));
        field.addCard(new Subject(50, 405, 962, 325, 1116, 91, 995, 171, 840));
        field.addCard(new Subject(17, 982, 911, 1000, 1056, 769, 1085, 751, 940));
        field.addCard(new Subject(38, 1511, 798, 1637, 910, 1463, 1105, 1337, 993));
        field.addCard(new Subject(44, 1269, 145, 1318, 34, 1516, 121, 1468, 232));
        field.addCard(new Subject(41, 720, 172, 719, 60, 901, 57, 902, 169));

        field.addCard(new Task(3, 377, 431, 263, 341, 415, 149, 529, 240));
        field.addCard(new Task(27, 478, 519, 373, 428, 528, 250, 633, 340));
        field.addCard(new Task(51, 486, 802, 409, 940, 191, 819, 268, 681));
        field.addCard(new Task(39, 583, 669, 503, 799, 271, 656, 351, 526));
        field.addCard(new Task(30, 976, 748, 985, 888, 763, 901, 755, 761));
        field.addCard(new Task(60, 956, 578, 966, 713, 753, 728, 743, 593));
        field.addCard(new Task(42, 1391, 662, 1502, 777, 1309, 965, 1198, 850));
        field.addCard(new Task(9, 1258, 523, 1374, 642, 1185, 827, 1069, 707));
        field.addCard(new Task(45, 1199, 272, 1253, 154, 1470, 252, 1416, 371));
        field.addCard(new Task(33, 1144, 401, 1199, 277, 1420, 376, 1364, 499));
        field.addCard(new Task(15, 718, 300, 718, 186, 911, 186, 911, 300));
        field.addCard(new Task(18, 706, 443, 706, 325, 909, 325, 909, 443));

        field.addCard(new Transfer(16, 236, 668, 99, 664, 107, 409, 244, 413));
        field.addCard(new Transfer(1, 704, 905, 674, 1062, 434, 1016, 464, 860));
        field.addCard(new Transfer(28, 1222, 838, 1264, 990, 1026, 1056, 984, 904));
        field.addCard(new Transfer(22, 1424, 429, 1556, 422, 1567, 641, 1435, 648));
        field.addCard(new Transfer(40, 999, 230, 1020, 109, 1222, 144, 1201, 265));
        field.addCard(new Transfer(34, 498, 210, 479, 96, 679, 64, 697, 179));

        return field;
    }

    public static Field2 getField12() {
        Field2 field = new Field2();

        field.setName("Beispiel 12");

        field.addCard(new Subject(8, 745, 447, 769, 706, 325, 748, 300, 490));
        field.addCard(new Subject(17, 1027, 1840, 1007, 2154, 505, 2122, 525, 1809));
        field.addCard(new Subject(38, 2366, 1601, 2645, 1394, 2982, 1851, 2703, 2057));
        field.addCard(new Subject(44, 2558, 521, 2605, 258, 3047, 335, 3001, 598));
        field.addCard(new Subject(50, 1470, 1158, 1419, 879, 1894, 791, 1945, 1071));

        field.addCard(new Task(51, 727, 1366, 727, 1102, 1188, 1102, 1188, 1366));
        field.addCard(new Task(60, 1529, 2170, 1529, 1840, 2015, 1840, 2015, 2170));
        field.addCard(new Task(39, 2267, 1065, 2328, 773, 2842, 879, 2781, 1171));
        field.addCard(new Task(18, 1818, 57, 1840, 300, 1410, 339, 1387, 96));

        return field;
    }

    public static Field2 getField13() {
        Field2 field = new Field2();

        field.setName("Beispiel 13");

        field.addCard(new Subject(8, 376, 237, 376, 346, 177, 346, 176, 237));
        field.addCard(new Subject(17, 456, 867, 431, 1016, 176, 974, 201, 825));
        field.addCard(new Subject(38, 1111, 769, 1250, 679, 1401, 911, 1263, 1002));
        field.addCard(new Subject(44, 1211, 289, 1232, 175, 1436, 213, 1415, 327));
        field.addCard(new Subject(50, 689, 553, 671, 428, 891, 397, 909, 522));

        field.addCard(new Task(51, 325, 629, 339, 510, 562, 538, 548, 656));
        field.addCard(new Task(60, 680, 1027, 695, 866, 944, 890, 929, 1051));
        field.addCard(new Task(39, 984, 526, 1010, 399, 1249, 449, 1223, 576));
        field.addCard(new Task(18, 875, 85, 880, 185, 684, 195, 679, 95));

        return field;
    }

    public static Field2 getField14() {
        Field2 field = new Field2();

        field.setName("Beispiel 14");

        field.addCard(new Subject(8, 606, 394, 614, 650, 163, 665, 154, 408));
        field.addCard(new Subject(17, 827, 1841, 784, 2181, 230, 2111, 273, 1772));
        field.addCard(new Subject(38, 2254, 1615, 2555, 1403, 2902, 1898, 2600, 2109));
        field.addCard(new Subject(44, 2477, 513, 2528, 247, 2983, 335, 2931, 601));
        field.addCard(new Subject(50, 1328, 1128, 1287, 841, 1779, 771, 1820, 1057));

        field.addCard(new Task(51, 685, 1316, 683, 1039, 1166, 1034, 1168, 1311));
        field.addCard(new Task(60, 1382, 1644, 1382, 1326, 1874, 1326, 1874, 1644));
        field.addCard(new Task(39, 1978, 1064, 2037, 770, 2571, 877, 2511, 1172));
        field.addCard(new Task(18, 1786, 409, 1798, 663, 1338, 685, 1326, 431));

        return field;
    }

    public static Field2 getField15() {
        Field2 field = new Field2();

        field.setName("Beispiel 15");

        field.addCard(new Subject(8, 383, 266, 395, 384, 194, 404, 182, 285));
        field.addCard(new Subject(17, 511, 895, 503, 1030, 284, 1016, 293, 882));
        field.addCard(new Subject(38, 1103, 791, 1227, 702, 1372, 902, 1248, 991));
        field.addCard(new Subject(44, 1201, 301, 1226, 179, 1427, 220, 1402, 342));
        field.addCard(new Subject(50, 709, 592, 688, 464, 898, 428, 920, 556));

        field.addCard(new Task(18, 855, -8, 864, 97, 671, 114, 662, 9));
        field.addCard(new Task(51, 150, 680, 141, 555, 351, 540, 360, 664));
        field.addCard(new Task(60, 781, 1198, 775, 1049, 993, 1040, 1000, 1189));
        field.addCard(new Task(39, 1296, 605, 1296, 474, 1518, 474, 1518, 605));


        return field;
    }

    public static Field2 getField16() {
        Field2 field = new Field2();

        field.setName("Beispiel 16");

        field.addCard(new Subject(8, 308, 221, 316, 336, 106, 350, 98, 234));
        field.addCard(new Subject(17, 440, 863, 427, 1017, 172, 995, 186, 841));
        field.addCard(new Subject(44, 1166, 226, 1180, 113, 1381, 139, 1366, 252));
        field.addCard(new Subject(50, 607, 516, 607, 393, 824, 393, 824, 516));
        field.addCard(new Subject(38, 1097, 717, 1227, 619, 1393, 839, 1262, 937));

        field.addCard(new Task(33, 305, 92, 307, 212, 90, 215, 89, 96));
        field.addCard(new Task(42, 355, 656, 369, 808, 132, 830, 117, 678));
        field.addCard(new Task(51, 63, 667, 58, 534, 286, 526, 290, 659));
        field.addCard(new Task(27, 1270, 958, 1146, 1092, 934, 895, 1059, 761));
        field.addCard(new Task(60, 742, 1069, 880, 925, 1105, 1141, 968, 1284));
        field.addCard(new Task(30, 934, 350, 1057, 348, 1062, 569, 939, 572));
        field.addCard(new Task(9, 1106, 345, 1234, 345, 1235, 572, 1107, 573));
        field.addCard(new Task(39, 1307, 341, 1433, 336, 1442, 578, 1316, 582));
        field.addCard(new Task(15, 1102, 273, 984, 245, 1036, 29, 1154, 57));
        field.addCard(new Task(3, 940, 235, 819, 206, 869, 1, 990, 30));
        field.addCard(new Task(18, 745, 188, 630, 156, 690, -60, 805, -28));

        return field;
    }

    public static Field2 getField17() {
        Field2 field = new Field2();

        field.setName("Beispiel 17");

        field.addCard(new Subject(8, 371, 246, 379, 363, 169, 378, 160, 261));
        field.addCard(new Subject(17, 504, 895, 492, 1049, 243, 1030, 255, 876));
        field.addCard(new Subject(44, 1232, 255, 1245, 140, 1452, 163, 1438, 278));
        field.addCard(new Subject(38, 1162, 755, 1295, 655, 1465, 878, 1332, 979));
        field.addCard(new Subject(50, 907, 612, 777, 612, 778, 393, 907, 393));

        field.addCard(new Task(33, 366, 116, 369, 237, 156, 243, 152, 122));
        field.addCard(new Task(42, 419, 685, 434, 839, 197, 862, 182, 708));
        field.addCard(new Task(51, 130, 697, 123, 562, 351, 552, 357, 687));
        field.addCard(new Task(27, 1339, 1000, 1213, 1134, 1000, 935, 1126, 800));
        field.addCard(new Task(60, 808, 1107, 946, 965, 1169, 1183, 1031, 1325));
        field.addCard(new Task(30, 997, 381, 1122, 379, 1126, 600, 1001, 603));
        field.addCard(new Task(9, 1170, 376, 1300, 374, 1304, 607, 1174, 609));
        field.addCard(new Task(39, 1375, 372, 1505, 367, 1514, 614, 1384, 619));
        field.addCard(new Task(15, 1169, 302, 1047, 274, 1097, 52, 1218, 80));
        field.addCard(new Task(3, 1005, 261, 881, 234, 928, 25, 1051, 53));
        field.addCard(new Task(18, 807, 213, 690, 182, 750, -35, 866, -3));

        return field;
    }
}
