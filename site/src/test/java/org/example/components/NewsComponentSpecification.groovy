package org.example.components
import org.example.beans.NewsDocument
import org.hippoecm.hst.content.beans.query.HstQuery
import org.hippoecm.hst.content.beans.query.HstQueryManager
import org.hippoecm.hst.content.beans.query.HstQueryResult
import org.hippoecm.hst.content.beans.standard.HippoBeanIterator
import org.hippoecm.hst.mock.content.beans.standard.MockHippoBean
import org.hippoecm.hst.mock.core.component.MockHstRequest
import org.hippoecm.hst.mock.core.component.MockHstResponse
import org.hippoecm.hst.mock.core.request.MockHstRequestContext
import spock.lang.Ignore
import spock.lang.Specification

class NewsComponentSpecification extends Specification {

  @Ignore
  def "Returns some news documents"() {

    given:
    def component = new NewsOverviewComponent()
    def response = new MockHstResponse()

    def requestContext = new MockHstRequestContext()
    requestContext.setContentBean new MockHippoBean()

    def request = new MockHstRequest()
    request.setRequestContext requestContext

    def queryManager = Mock(HstQueryManager)
    requestContext.setDefaultHstQueryManager queryManager

    def hstQuery = Mock(HstQuery)
    def queryResult = Mock(HstQueryResult)
    def beanIterator = Mock(HippoBeanIterator)

    when:
    component.doBeforeRender(request, response)

    then:
    1 * request.setAttribute("newsItems", _)
    1 * queryManager.createQuery(_, true, NewsDocument) >> hstQuery
    1 * hstQuery.addOrderByAscending("gogreen:date")
    1 * hstQuery.execute() >> queryResult
    _ * queryResult.hippoBeans >> beanIterator

  }
}
