package org.example.components;

import org.example.beans.NewsDocument;
import org.hippoecm.hst.component.support.bean.BaseHstComponent;
import org.hippoecm.hst.content.beans.query.HstQuery;
import org.hippoecm.hst.content.beans.query.HstQueryResult;
import org.hippoecm.hst.content.beans.query.exceptions.QueryException;
import org.hippoecm.hst.content.beans.standard.HippoBean;
import org.hippoecm.hst.core.component.HstComponentException;
import org.hippoecm.hst.core.component.HstRequest;
import org.hippoecm.hst.core.component.HstResponse;
import org.hippoecm.hst.core.request.HstRequestContext;

import java.util.ArrayList;
import java.util.List;

public class NewsOverviewComponent extends BaseHstComponent {

    @Override
    public void doBeforeRender(HstRequest request, HstResponse response) throws HstComponentException {
        HstRequestContext requestContext = request.getRequestContext();
        HippoBean contentBean = requestContext.getContentBean();
        try {
            HstQuery query = requestContext.getQueryManager().createQuery(contentBean, true, NewsDocument.class);
            query.addOrderByAscending("gogreen:date");
            HstQueryResult result = query.execute();
            List<HippoBean> beans = new ArrayList<>(result.getSize());
            result.getHippoBeans().forEachRemaining(beans::add);
            request.setAttribute("newsItems", beans);
        } catch (QueryException e) {
            throw new HstComponentException(e);
        }
    }
}
