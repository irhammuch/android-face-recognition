package com.irhammuch.android.facerecognition;

public interface SimilarityClassifier {
    /** An immutable result returned by a Classifier describing what was recognized. */
    class Recognition {
        /**
         * A unique identifier for what has been recognized. Specific to the class, not the instance of
         * the object.
         */
        private final String id;
        /** Display name for the recognition. */
        private final String title;


        private final Float distance;
        private Object extra;

        public Recognition(
                final String id, final String title, final Float distance) {
            this.id = id;
            this.title = title;
            this.distance = distance;
            this.extra = null;

        }

        public void setExtra(Object extra) {
            this.extra = extra;
        }
        public Object getExtra() {
            return this.extra;
        }

        @Override
        public String toString() {
            String resultString = "";
            if (id != null) {
                resultString += "[" + id + "] ";
            }

            if (title != null) {
                resultString += title + " ";
            }

            if (distance != null) {
                resultString += String.format("(%.1f%%) ", distance * 100.0f);
            }

            return resultString.trim();
        }

    }
}
