-- https://docs.spring.io/spring-modulith/reference/appendix.html#schemas
-- I had issues because the default value for serialized_event is varchar(255)
-- to tackle this I need to initialize the event_publication table as suggested in following forum topic: https://github.com/spring-projects/spring-modulith/issues/519#issuecomment-1967395525

CREATE TABLE IF NOT EXISTS event_publication
(
  id               UUID NOT NULL,
  listener_id      TEXT NOT NULL,
  event_type       TEXT NOT NULL,
  serialized_event TEXT NOT NULL,
  publication_date TIMESTAMP WITH TIME ZONE NOT NULL,
  completion_date  TIMESTAMP WITH TIME ZONE,
  PRIMARY KEY (id)
);
CREATE INDEX IF NOT EXISTS event_publication_serialized_event_hash_idx ON event_publication USING hash(serialized_event);
CREATE INDEX IF NOT EXISTS event_publication_by_completion_date_idx ON event_publication (completion_date);