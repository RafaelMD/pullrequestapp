CREATE KEYSPACE PullRequestListKeySpace WITH REPLICATION = {'class': 'SimpleStrategy', 'replication_factor': 1};

USE PullRequestListKeySpace;

CREATE TABLE PullRequest (
  id UUID PRIMARY KEY,
  url TEXT,
  branch TEXT,
  comment TEXT
);