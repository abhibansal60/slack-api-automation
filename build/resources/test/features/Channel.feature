Feature: Slack channel operations and validations

  Background:
    Given user has access to the slack api

  @Join
  Scenario Outline: Create and join a channel
    When user creates channel "<channel>"
    Then user joins the channel
    Examples:
      | channel |
      | ab_channel_01   |

  @Rename
  Scenario Outline: Rename a channel
    When user renames the channel "<channel>" with "<newname>"
    Then validate channel is renamed successfully
    Examples:
      | channel | newname |
      |  ab_channel_01  | test_ab_channel_01  |

  @Archive
  Scenario Outline: Archive a channel
    When user archives the channel "<channel>"
    Then validate channel is archived successfully
    Examples:
      | channel |
      | test_ab_channel_01   |


