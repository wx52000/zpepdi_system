package com.zpepdi.qj_heating.opcvTest;

import java.util.List;

public class JsonBean {
    private List<B> tables_result;

    public List<B> getTables_result() {
        return tables_result;
    }

    public void setTables_result(List<B> tables_result) {
        this.tables_result = tables_result;
    }

    public static class B{
//        private List<Object> table_location;
        private List<header> header;
        private List<body> body;
//        private List<Object> footer;


        public List<B.header> getHeader() {
            return header;
        }

        public void setHeader(List<B.header> header) {
            this.header = header;
        }

        public List<B.body> getBody() {
            return body;
        }

        public void setBody(List<B.body> body) {
            this.body = body;
        }

        @Override
        public String toString() {
            return "B{" +
                    "header=" + header +
                    ", body=" + body +
                    '}';
        }

        public static class header{
            private String words;

            public String getWords() {
                return words;
            }

            public void setWords(String words) {
                this.words = words;
            }

            @Override
            public String toString() {
                return "header{" +
                        "words='" + words + '\'' +
                        '}';
            }
        }
        public static class body{
            private String col_start;
            private String row_start;
            private String row_end;
            private String col_end;
            private String words;

            public String getCol_start() {
                return col_start;
            }

            public void setCol_start(String col_start) {
                this.col_start = col_start;
            }

            public String getRow_start() {
                return row_start;
            }

            public void setRow_start(String row_start) {
                this.row_start = row_start;
            }

            public String getRow_end() {
                return row_end;
            }

            public void setRow_end(String row_end) {
                this.row_end = row_end;
            }

            public String getCol_end() {
                return col_end;
            }

            public void setCol_end(String col_end) {
                this.col_end = col_end;
            }

            public String getWords() {
                return words;
            }

            public void setWords(String words) {
                this.words = words;
            }

            @Override
            public String toString() {
                return "body{" +
                        "col_start='" + col_start + '\'' +
                        ", row_start='" + row_start + '\'' +
                        ", row_end='" + row_end + '\'' +
                        ", col_end='" + col_end + '\'' +
                        ", words='" + words + '\'' +
                        '}';
            }
        }

    }

    @Override
    public String toString() {
        return "JsonBean{" +
                "tables_result=" + tables_result +
                '}';
    }
}
