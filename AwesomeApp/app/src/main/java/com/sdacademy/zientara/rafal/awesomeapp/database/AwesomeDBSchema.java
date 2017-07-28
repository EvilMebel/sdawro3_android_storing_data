package com.sdacademy.zientara.rafal.awesomeapp.database;

public final class AwesomeDBSchema {

    public static final String DB_NAME = "AwesomeAppDB";

    public final class Tables {
        public static final String News = "news";
    }

    public static final class News {

        public static final class Column {
            public static final String Id = "Id";
            public static final String Name = "Name";
            public static final String Description = "Description";
            public static final String DateStart = "DateStart";
            public static final String DateFinish = "DateFinish";
            public static final String isAsset = "isAsset";
            public static final String AllColumns[] = {
                    Id,
                    Name,
                    Description,
                    DateStart,
                    DateFinish,
                    isAsset
            };
        }

        public static final class ColumnID {
            public static final int Id = 0;
            public static final int Name = 1;
            public static final int Description = 2;
            public static final int DateStart = 3;
            public static final int DateFinish = 4;
            public static final int isAsset = 5;
        }
    }
}
