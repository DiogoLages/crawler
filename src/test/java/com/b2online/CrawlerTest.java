package com.b2online;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import org.jsoup.select.NodeFilter;
import org.junit.jupiter.api.Test;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Objects;
import java.util.regex.Pattern;

public class CrawlerTest {

    private static final Pattern patternA = Pattern.compile("([A-Z]{3})-(\\d{4})-(\\d{3})", Pattern.CASE_INSENSITIVE);
    private static final Pattern patternB = Pattern.compile("([A-Z]{1}\\d{2})-(\\d{4})-(\\d{3})", Pattern.CASE_INSENSITIVE);
    private static final String URL_REFACTORING = "https://www.netshoes.com.br/refactoring";
    private static final String SELLER_ID_B2_ONLINE = "8265";
    private static final String SELLER_ID_NET_SHOES = "0";
    private static final String SELLER_ID_ASICS = "12138";

    @Test
    public void test() throws IOException {

        // TODO serauma lista de links
        String url = "https://www.netshoes.com.br/D18-5540-172-43";
        String urlHref = "";

        Document document = Jsoup.connect(url).get();
        Elements buyBox = document.select("#buy-box");
        Elements productListItem = buyBox.select("div.sku-select li a.product-item");

        String[] split = url.split("/");
        String skuCompleto = "";

        if (split.length > 0) {
            skuCompleto = split[split.length - 1];
        }

        if (StringUtils.hasText(skuCompleto)) {
            Temp temp = new Temp(skuCompleto);
            productListItem.filter(temp);

            String sellerId = temp.getNodeImplents().attr("data-sellerid");

            urlHref = temp.getNodeImplents().attr("href");

            if((Objects.nonNull(sellerId)) && (sellerId != SELLER_ID_B2_ONLINE || sellerId != SELLER_ID_NET_SHOES || sellerId != SELLER_ID_ASICS)) {
                String temp1 = URL_REFACTORING+urlHref;
                Document documentClick = Jsoup.connect(temp1).get();
                // TODO select para capturar opreco
                System.out.println(skuCompleto + " " + documentClick.select("div.default-price strong").first().text().replace("R$", ""));
            } else {

                System.out.println("");
            }
        } else {
            System.out.println("Deu ruim!");
        }
        //documentCick.getAllElements().stream().forEach(p -> System.out.println(p.getAllElements()));
    }

    class Temp implements NodeFilter {

        private String skuCompleto;
        private Node nodeImplents;

        public Temp(String skuCompleto) {
            this.skuCompleto = skuCompleto;
        }

        @Override
        public FilterResult head(Node node, int i) {
            FilterResult filterResult = FilterResult.CONTINUE;
            if (node.attr("href").contains(skuCompleto)) {
                nodeImplents = node;
                filterResult = FilterResult.STOP;
            }
            return filterResult;
        }

        @Override
        public FilterResult tail(Node node, int i) {
            return null;
        }

        public Node getNodeImplents() {
            return nodeImplents;
        }
    }
}
