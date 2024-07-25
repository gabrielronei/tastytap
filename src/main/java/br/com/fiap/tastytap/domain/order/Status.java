package br.com.fiap.tastytap.domain.order;

public enum Status {

    RECEIVED {
        @Override
        public Status next() {
            return PREPARING;
        }
    },
    PREPARING {
        @Override
        public Status next() {
            return DONE;
        }
    },
    DONE {
        @Override
        public Status next() {
            return FINISHED;
        }
    },
    FINISHED {
        @Override
        public Status next() {
            return FINISHED;
        }
    };

    public abstract Status next();

    public boolean hasFinished() {
        return FINISHED == this;
    }
}