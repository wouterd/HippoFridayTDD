# Simple news service

This is a very simple REST API for News.

Try to build these requirements using TDD, one requirement at a time, without looking forward. Emerging architecture is what you want.

## The requirements

- Should give all the news items when a GET request is done to `/news`
  - Should return an empty list and not a 404 when there is no news.

- Should allow you to POST a new news item to `/news`
  - And then returns a `Location` header with the URL to the newly created item.

- Should return only one news item when a GET request is done to `/news/{id}`
  - Should return a 404 when no such news item can be found

- News Items can have tags

- A GET to `/news/tagged-by/{tag}` returns all news items tagged by that {tag}.

- By posting a tag name to `/news/{id}/tags`, you can add a tag to a news item.

- The maximum amount of news items returned by a GET request can be limited by adding a `limit` request parameter to
the request. For example: `/news?limit=10`

- News that's older than 100 days is not news.

- News should be ordered by date, latest news first.

- News items can become quite bulky, so we want to be able to give small and big responses. A `type` request parameter
 can be set to `simple` to only return the title, excerpt and date of a news item.
 
- A `fields` request parameter can be added with a comma separated list of root fields to return from a news item. 
As so: `/news/tagged-by/politics?fields=title,date`. Only the specified fields should be returned.