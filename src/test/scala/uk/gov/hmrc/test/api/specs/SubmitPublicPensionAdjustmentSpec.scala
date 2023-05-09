/*
 * Copyright 2023 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.hmrc.test.api.specs

import play.api.libs.ws.StandaloneWSResponse
import uk.gov.hmrc.test.api.models.{SubmitPublicPensionAdjustmentRequest, SubmitPublicPensionAdjustmentResponse}
import uk.gov.hmrc.test.api.utils.ApiLogger

class SubmitPublicPensionAdjustmentSpec extends BaseSpec {
  val uri = "submit-public-pension-adjustment/calculation/submit"
  Feature("Submit Public Pension Adjustment API functionality") {

    Scenario("Verify submit public pension adjustment API response") {

      Given("I got a valid bearer token")
      val authBearerToken: String                           = authHelper.getAuthBearerToken
      val requestJson: SubmitPublicPensionAdjustmentRequest =
        SubmitPublicPensionAdjustmentRequest(dataItem1 = "theDataItem")

      When("I use the submit public pension adjustment API request to get a valid response")
      val response: StandaloneWSResponse = individualsMatchingHelper.postRequest(uri, requestJson, authBearerToken)

      Then("I got the status code 200")
      response.status shouldBe 200

      Then("I got the response body parameter - submissionReference")
      val responseBody: SubmitPublicPensionAdjustmentResponse = individualsMatchingHelper.readResponse(response.body)
      ApiLogger.log.info("submissionReference value : " + responseBody.submissionReference)
    }
  }
}
