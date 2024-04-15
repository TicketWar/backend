package com.ticketwar.ticketwar.configure;

import static org.springframework.util.StringUtils.hasText;

import com.p6spy.engine.common.ConnectionInformation;
import com.p6spy.engine.event.JdbcEventListener;
import com.p6spy.engine.logging.Category;
import com.p6spy.engine.spy.P6SpyOptions;
import com.p6spy.engine.spy.appender.MessageFormattingStrategy;
import java.sql.SQLException;
import java.util.Locale;
import org.hibernate.engine.jdbc.internal.FormatStyle;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile({"default", "local", "dev"}) // WARN: Do not use in production mode.
@Component
public class P6SpySqlFormatter extends JdbcEventListener implements MessageFormattingStrategy {

  @Override
  public void onAfterGetConnection(ConnectionInformation connectionInformation, SQLException e) {
    P6SpyOptions.getActiveInstance().setLogMessageFormat(this.getClass().getName());
  }

  @Override
  public String formatMessage(int connectionId, String now, long elapsed, String category, String prepared, String sql, String url) {
    return highlight(format(category, sql));
  }

  private String highlight(String sql) {
    return FormatStyle.HIGHLIGHT.getFormatter().format(sql);
  }

  private String format(String category, String sql) {
    if (hasText(sql) && isStatement(category)) {
      if (isDDL(trim(sql))) {
        return FormatStyle.DDL.getFormatter().format(sql);
      }
      return FormatStyle.BASIC.getFormatter().format(sql);
    }
    return sql;
  }

  private static boolean isDDL(String trimmedSQL) {
    return trimmedSQL.startsWith("create")
        || trimmedSQL.startsWith("alter")
        || trimmedSQL.startsWith("drop")
        || trimmedSQL.startsWith("comment");
  }

  private static String trim(String sql) {
    return sql.trim().toLowerCase(Locale.ROOT);
  }

  private static boolean isStatement(String category) {
    return Category.STATEMENT.getName().equals(category);
  }
}