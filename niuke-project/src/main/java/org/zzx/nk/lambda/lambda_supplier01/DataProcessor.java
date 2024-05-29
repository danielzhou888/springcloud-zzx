package org.zzx.nk.lambda.lambda_supplier01;

import java.util.function.Supplier;

/**
 * 案例5：大数据处理
 * 在大数据处理应用中，初始化数据处理器可能是一个昂贵的操作。我们可以使用 Supplier 来延迟初始化数据处理器，只有在需要时才创建处理器。
 */
public class DataProcessor {
    private Supplier<Processor> processorSupplier = this::createProcessor;
    private Processor processor;

    private Processor createProcessor() {
        // 模拟创建数据处理器
        System.out.println("Creating data processor...");
        return new Processor();
    }

    public Processor getProcessor() {
        if (processor == null) {
            processor = processorSupplier.get();
        }
        return processor;
    }

    public static void main(String[] args) {
        DataProcessor dataProcessor = new DataProcessor();
        // 第一次访问时创建处理器
        Processor processor1 = dataProcessor.getProcessor();
        // 后续访问使用已创建的处理器
        Processor processor2 = dataProcessor.getProcessor();
    }
}

class Processor {
    // 模拟数据处理器类
}