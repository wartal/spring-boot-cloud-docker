package com.wartal;

import com.wartal.entity.TrendEntity;
import com.wartal.twitterapi.Trend;
import org.springframework.stereotype.Component;

/**
 * Created by lwartalski on 11/10/16.
 */
@Component
public class TrendMapper {

    /**
     * Map trend dto to trend entity
     *
     * @param source dto
     * @return Entity
     */
    public TrendEntity map(final Trend source) {
        if (source == null) {
            return null;
        }
        final TrendEntity dest = new TrendEntity();

        return map(source, dest);
    }

    private TrendEntity map(Trend source, TrendEntity dest) {
        dest.setName(source.getName());
        dest.setQuery(source.getQuery());
        dest.setTweetVolume(source.getTweetVolume());
        return dest;
    }

}
