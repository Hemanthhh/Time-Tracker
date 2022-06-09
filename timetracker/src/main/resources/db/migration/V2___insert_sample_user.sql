INSERT INTO `timetracker`.`users` (
  `id`, `created_by`, `email`,
  `first_name`, `last_name`, `updated_by`,
  `user_name`, `created_date`
)
VALUES
  (
    1, 'admin', 'john@paychex.com',
    'john', 'j', null, 'john', NOW()
  );