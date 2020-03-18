package com.slack.api.stepdefinitions;
import com.slack.api.ServiceConfig;
import com.slack.api.utils.RestUtil;
import com.slack.api.world.APIWorld;
import io.cucumber.java8.En;
import io.restassured.response.Response;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;


@ContextConfiguration(classes = {ServiceConfig.class})
public class Channel implements En {
    @Autowired
    RestUtil restUtil;
    @Autowired
    APIWorld apiWorld;

    public Channel() {
        Given("^user has access to the slack api$", () -> Assert.assertNotNull(apiWorld.getBearerToken()));
        When("^user creates channel \"([^\"]*)\"$", (String channel) -> {
            apiWorld.setChannelName(channel);
            Response response = restUtil.postData("channels.create","name",apiWorld.getChannelName());
            //Validate name of channel created
            Assert.assertEquals(apiWorld.getChannelName(),response.jsonPath().get("channel.name"));
            apiWorld.setChannelId(restUtil.getChannelAttribute(apiWorld.getChannelName(),"id"));
        });
        And("^user joins the channel$", () -> restUtil.postData("channels.join","name",apiWorld.getChannelName()));

        When("^user renames the channel \"([^\"]*)\" with \"([^\"]*)\"$", (String channel, String newName) -> {
            if(channel.length()>0) {
                apiWorld.setChannelId(restUtil.getChannelAttribute(channel, "id"));
                apiWorld.setUpdatedChannelName(newName);
            }
            else apiWorld.setUpdatedChannelName(apiWorld.getChannelName()+"_updated");
            restUtil.postData("channels.rename","channel",apiWorld.getChannelId(),"name",apiWorld.getUpdatedChannelName());
        });

        Then("^validate channel is renamed successfully$", () -> Assert.assertEquals(restUtil.getChannelAttribute(apiWorld.getUpdatedChannelName(),"id"),apiWorld.getChannelId()));
        When("^user archives the channel \"([^\"]*)\"$", (String channel) -> {
            if(channel.length()>0) {
                apiWorld.setChannelId(restUtil.getChannelAttribute(channel, "id"));
            }
            String isArchived=restUtil.getChannelAttribute(apiWorld.getUpdatedChannelName(),"is_archived");
            Assert.assertEquals("Channel already archived!","false",isArchived);
            restUtil.postData("channels.archive","channel",apiWorld.getChannelId());
        });
        Then("^validate channel is archived successfully$", () -> {
            String isArchived=restUtil.getChannelAttribute(apiWorld.getUpdatedChannelName(),"is_archived");
            Assert.assertEquals("true",isArchived);
        });

    }
}
